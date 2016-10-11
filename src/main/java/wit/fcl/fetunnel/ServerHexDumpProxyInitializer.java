package wit.fcl.fetunnel;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

public class ServerHexDumpProxyInitializer extends ChannelInitializer<SocketChannel> {

    private final String remoteHost;
    private final int remotePort;

    public ServerHexDumpProxyInitializer(String remoteHost, int remotePort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
    }

    @Override
    public void initChannel(SocketChannel ch) {
    	
    	SelfSignedCertificate ssc = null;
        SslContext sslCtx = null;
		try {
			ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        ch.pipeline().addLast(
                sslCtx.newHandler(ch.alloc(),remoteHost,remotePort),
                new LoggingHandler(LogLevel.INFO),
                new ServerHexDumpProxyFrontendHandler(remoteHost, remotePort));
    }
}
