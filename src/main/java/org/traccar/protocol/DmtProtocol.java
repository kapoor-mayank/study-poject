package org.traccar.protocol;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.traccar.*;


public class DmtProtocol
        extends BaseProtocol {
    private static final Logger LOGGER = LoggerFactory.getLogger(DmtProtocol.class);
    public DmtProtocol() {
        LOGGER.info("DmtProtocol initialised");
        addServer(new TrackerServer(false, getName()) {
            protected void addProtocolHandlers(PipelineBuilder pipeline) {
                pipeline.addLast((ChannelHandler) new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 1024, 3, 2, 0, 0, true));
                pipeline.addLast((ChannelHandler) new DmtProtocolDecoder((Protocol) DmtProtocol.this));
            }
        });
    }
}


/* Location:              C:\User\\user\Documents\Ensurity Mobile [Client]\Latest App\traccar\tracker-server.jar!\org\traccar\protocol\DmtProtocol.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */