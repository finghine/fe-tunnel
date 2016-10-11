package wit.fcl.fetunnel;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public final class ServerHexDumpProxy {

    public static void main(String[] args) throws Exception {
    	run(8082,"localhost",8083);
    }
    public static void run(int local_port,String remote_host,int remote_port) throws Exception 
    {
    	   System.err.println("Proxying *:" + local_port + " to " + remote_host + ':' + remote_port + " ...");
           // Configure the bootstrap.
           EventLoopGroup bossGroup = new NioEventLoopGroup(1);
           EventLoopGroup workerGroup = new NioEventLoopGroup();
           try {
               ServerBootstrap b = new ServerBootstrap();
               b.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .handler(new LoggingHandler(LogLevel.INFO))
                .childHandler(new ServerHexDumpProxyInitializer(remote_host, remote_port))
                .childOption(ChannelOption.AUTO_READ, false)
                .bind(local_port).sync().channel().closeFuture().sync();
           } finally {
               bossGroup.shutdownGracefully();
               workerGroup.shutdownGracefully();
           }
    }
}
