package org.traccar;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.LinkedList;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.config.Keys;
import org.traccar.handler.ComputedAttributesHandler;
import org.traccar.handler.CopyAttributesHandler;
import org.traccar.handler.DefaultDataHandler;
import org.traccar.handler.DistanceHandler;
import org.traccar.handler.EngineHoursHandler;
import org.traccar.handler.FilterHandler;
import org.traccar.handler.ForwarderHandler;
import org.traccar.handler.GeocoderHandler;
import org.traccar.handler.GeolocationHandler;
import org.traccar.handler.HemisphereHandler;
import org.traccar.handler.MotionHandler;
import org.traccar.handler.NetworkMessageHandler;
import org.traccar.handler.OpenChannelHandler;
import org.traccar.handler.RemoteAddressHandler;
import org.traccar.handler.StandardLoggingHandler;
import org.traccar.handler.TimeHandler;
import org.traccar.handler.events.AlertEventHandler;
import org.traccar.handler.events.CommandResultEventHandler;
import org.traccar.handler.events.DriverEventHandler;
import org.traccar.handler.events.FuelDropEventHandler;
import org.traccar.handler.events.GeofenceEventHandler;
import org.traccar.handler.events.IgnitionEventHandler;
import org.traccar.handler.events.MaintenanceEventHandler;
import org.traccar.handler.events.MotionEventHandler;
import org.traccar.handler.events.OverspeedEventHandler;

public abstract class BasePipelineFactory extends ChannelInitializer<Channel> {
    private static final Logger LOGGER = LoggerFactory.getLogger(BasePipelineFactory.class);

    private final TrackerConnector connector;

    private final String protocol;

    private boolean eventsEnabled;

    private int timeout;

    public BasePipelineFactory(TrackerConnector connector, String protocol) {
        this.connector = connector;
        this.protocol = protocol;
        this.eventsEnabled = Context.getConfig().getBoolean(Keys.EVENT_ENABLE);
        this.timeout = Context.getConfig().getInteger(Keys.PROTOCOL_TIMEOUT.withPrefix(protocol));
        if (this.timeout == 0)
            this.timeout = Context.getConfig().getInteger(Keys.SERVER_TIMEOUT);
    }

    private void addHandlers(ChannelPipeline pipeline, Class<? extends ChannelHandler>... handlerClasses) {
        for (Class<? extends ChannelHandler> handlerClass : handlerClasses) {
            if (handlerClass != null)
                pipeline.addLast(new ChannelHandler[] { (ChannelHandler)Main.getInjector().getInstance(handlerClass) });
        }
    }

    public static <T extends ChannelHandler> T getHandler(ChannelPipeline pipeline, Class<T> clazz) {
        for (Map.Entry<String, ChannelHandler> handlerEntry : (Iterable<Map.Entry<String, ChannelHandler>>)pipeline) {
            ChannelInboundHandler channelInboundHandler = null;
            ChannelOutboundHandler channelOutboundHandler = null;
            ChannelHandler handler = handlerEntry.getValue();
            if (handler instanceof WrapperInboundHandler) {
                channelInboundHandler = ((WrapperInboundHandler)handler).getWrappedHandler();
            } else if (channelInboundHandler instanceof WrapperOutboundHandler) {
                channelOutboundHandler = ((WrapperOutboundHandler)channelInboundHandler).getWrappedHandler();
            }
            if (clazz.isAssignableFrom(channelOutboundHandler.getClass()))
                return (T)channelOutboundHandler;
        }
        return null;
    }

    protected void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        LinkedList<ChannelHandler[]> rec$ = null;
        addTransportHandlers(xva$0 -> rec$.addLast(new ChannelHandler[] { xva$0 }));
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new ForwarderHandler() });
        if (this.timeout > 0 && !this.connector.isDatagram())
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new IdleStateHandler(this.timeout, 0, 0) });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new OpenChannelHandler(this.connector) });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new NetworkMessageHandler() });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new StandardLoggingHandler() });
        addProtocolHandlers(handler -> {
            WrapperOutboundHandler wrapperOutboundHandler = null;
            if (!(handler instanceof BaseProtocolDecoder) && !(handler instanceof BaseProtocolEncoder)) {
                WrapperInboundHandler wrapperInboundHandler = null;
                if (handler instanceof ChannelInboundHandler) {
                    wrapperInboundHandler = new WrapperInboundHandler((ChannelInboundHandler)handler);
                } else {
                    wrapperOutboundHandler = new WrapperOutboundHandler((ChannelOutboundHandler)wrapperInboundHandler);
                }
            }
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)wrapperOutboundHandler });
        });
        addHandlers(pipeline, (Class<? extends ChannelHandler>[])new Class[] { TimeHandler.class, GeolocationHandler.class, HemisphereHandler.class, DistanceHandler.class, RemoteAddressHandler.class });
        addDynamicHandlers(pipeline);
        addHandlers(pipeline, (Class<? extends ChannelHandler>[])new Class[] { FilterHandler.class, GeocoderHandler.class, MotionHandler.class, EngineHoursHandler.class, CopyAttributesHandler.class, ComputedAttributesHandler.class, WebDataHandler.class, DefaultDataHandler.class });
        if (Context.getRedisManager() != null)
            pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new RedisHandler() });
        if (this.eventsEnabled)
            addHandlers(pipeline, (Class<? extends ChannelHandler>[])new Class[] { CommandResultEventHandler.class, OverspeedEventHandler.class, FuelDropEventHandler.class, MotionEventHandler.class, GeofenceEventHandler.class, AlertEventHandler.class, IgnitionEventHandler.class, MaintenanceEventHandler.class, DriverEventHandler.class });
        pipeline.addLast(new ChannelHandler[] { (ChannelHandler)new MainEventHandler() });
    }

    private void addDynamicHandlers(ChannelPipeline pipeline) {
        String handlers = Context.getConfig().getString(Keys.EXTRA_HANDLERS);
        if (handlers != null)
            for (String handler : handlers.split(",")) {
                try {
                    pipeline.addLast(new ChannelHandler[] {(ChannelHandler) Class.forName(handler).getDeclaredConstructor(new Class[0]).newInstance(new Object[0])});
                } catch (ReflectiveOperationException error) {
                    LOGGER.warn("Dynamic handler error", error);
                }
            }
    }

    protected abstract void addTransportHandlers(PipelineBuilder paramPipelineBuilder);

    protected abstract void addProtocolHandlers(PipelineBuilder paramPipelineBuilder);
}
