package org.traccar;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.config.Keys;
import org.traccar.handler.*;
import org.traccar.handler.events.*;

import java.util.LinkedList;
import java.util.Map;

public abstract class BasePipelineFactory extends ChannelInitializer<Channel> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasePipelineFactory.class);

    private final TrackerConnector connector;
    private final String protocol;

    private boolean eventsEnabled;
    private int timeout;

    public BasePipelineFactory(TrackerConnector connector, String protocol) {
        LOGGER.info("BasePipeLineFactory initialised for: " + protocol);
        this.connector = connector;
        this.protocol = protocol;
        this.eventsEnabled = Context.getConfig().getBoolean(Keys.EVENT_ENABLE);
        this.timeout = Context.getConfig().getInteger(Keys.PROTOCOL_TIMEOUT.withPrefix(protocol));
        if (this.timeout == 0) {
            this.timeout = Context.getConfig().getInteger(Keys.SERVER_TIMEOUT);
        }
    }

    private void addHandlers(ChannelPipeline pipeline, Class<? extends ChannelHandler>... handlerClasses) {
        for (Class<? extends ChannelHandler> handlerClass : handlerClasses) {
            if (handlerClass != null) {
                pipeline.addLast(Main.getInjector().getInstance(handlerClass));
            }
        }
    }

    public static <T extends ChannelHandler> T getHandler(ChannelPipeline pipeline, Class<T> clazz) {
        for (Map.Entry<String, ChannelHandler> handlerEntry : pipeline) {
            ChannelHandler handler = handlerEntry.getValue();
            if (handler instanceof WrapperInboundHandler) {
                handler = ((WrapperInboundHandler) handler).getWrappedHandler();
            } else if (handler instanceof WrapperOutboundHandler) {
                handler = ((WrapperOutboundHandler) handler).getWrappedHandler();
            }
            if (clazz.isAssignableFrom(handler.getClass())) {
                return clazz.cast(handler);
            }
        }
        return null;
    }

    @Override
    protected void initChannel(Channel channel) {
        LOGGER.info("BasePipeLineFactory initChannel for: "+ channel);
        ChannelPipeline pipeline = channel.pipeline();

        addTransportHandlers(pipeline::addLast);

        pipeline.addLast(new ForwarderHandler());

        if (timeout > 0 && !connector.isDatagram()) {
            pipeline.addLast(new IdleStateHandler(timeout, 0, 0));
        }

        pipeline.addLast(new OpenChannelHandler(connector));
        pipeline.addLast(new NetworkMessageHandler());
        pipeline.addLast(new StandardLoggingHandler());

        // Corrected logic for wrapping inbound and outbound handlers
        addProtocolHandlers(handler -> {
            if (handler instanceof ChannelInboundHandler) {
                WrapperInboundHandler wrapperInboundHandler = new WrapperInboundHandler((ChannelInboundHandler) handler);
                pipeline.addLast(wrapperInboundHandler);
            } else if (handler instanceof ChannelOutboundHandler) {
                WrapperOutboundHandler wrapperOutboundHandler = new WrapperOutboundHandler((ChannelOutboundHandler) handler);
                pipeline.addLast(wrapperOutboundHandler);
            }
        });

        addHandlers(pipeline, TimeHandler.class, GeolocationHandler.class, HemisphereHandler.class,
                DistanceHandler.class, RemoteAddressHandler.class);
        addDynamicHandlers(pipeline);
        addHandlers(pipeline, FilterHandler.class, GeocoderHandler.class, MotionHandler.class, EngineHoursHandler.class,
                CopyAttributesHandler.class, ComputedAttributesHandler.class, WebDataHandler.class, DefaultDataHandler.class);

        if (Context.getRedisManager() != null) {
            pipeline.addLast(new RedisHandler());
        }

        if (eventsEnabled) {
            addHandlers(pipeline, CommandResultEventHandler.class, OverspeedEventHandler.class, FuelDropEventHandler.class,
                    MotionEventHandler.class, GeofenceEventHandler.class, AlertEventHandler.class,
                    IgnitionEventHandler.class, MaintenanceEventHandler.class, DriverEventHandler.class);
        }

        pipeline.addLast(new MainEventHandler());
    }

    private void addDynamicHandlers(ChannelPipeline pipeline) {
        String handlers = Context.getConfig().getString(Keys.EXTRA_HANDLERS);
        if (handlers != null) {
            for (String handler : handlers.split(",")) {
                try {
                    pipeline.addLast((ChannelHandler) Class.forName(handler).getDeclaredConstructor().newInstance());
                } catch (ReflectiveOperationException error) {
                    LOGGER.warn("Dynamic handler error", error);
                }
            }
        }
    }

    protected abstract void addTransportHandlers(PipelineBuilder pipelineBuilder);

    protected abstract void addProtocolHandlers(PipelineBuilder pipelineBuilder);
}
