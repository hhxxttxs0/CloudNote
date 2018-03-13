package pers.android.clientTest;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class ClientStartTest {

	public void connect(int port,String host) throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY,true)
			 .handler(new ChannelInitializer<SocketChannel>(){
				 public void initChannel(SocketChannel ch) throws Exception{
					 ch.pipeline().addLast(new ObjectEncoder());
					 ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
					 ch.pipeline().addLast(new ClientHandler());
				 }});
			//发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			
			f.channel().closeFuture().sync();
			}finally{
				group.shutdownGracefully();
			}
			 
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int port = 8888;
		if(args!=null&&args.length>0){
			try{
				port = Integer.valueOf(0);
			}catch(NumberFormatException e){
				
			}
		}
		new ClientStartTest().connect(8888,"localhost");
	}

}
