package com.mycloudnote.net.client;

import android.util.Log;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
//import org.jboss.netty.com.mycloudnote.net.handler.codec.serialization.ClassResolvers;
//import org.jboss.netty.com.mycloudnote.net.handler.codec.serialization.ObjectDecoder;
//import org.jboss.netty.com.mycloudnote.net.handler.codec.serialization.ObjectEncoder;
import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.net.thread.ConnectThread;

public class NoteClientBootstrap {
	private String host;
	private int port;
	
	public NoteClientBootstrap(String host,int port){
		this.host = host;
		this.port = port;
	}
	public void connect() throws Exception{
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
			 .option(ChannelOption.TCP_NODELAY,true)
			 .handler(new ChannelInitializer<SocketChannel>(){
				 public void initChannel(SocketChannel ch) throws Exception{
					 ch.pipeline().addLast(new ObjectEncoder());
					 ch.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(this.getClass().getClassLoader())));
					 ch.pipeline().addLast(new NoteClientHandler());
				 }});
			Channel ch = null;
			try {
				//发起异步连接操作
				ch = b.connect(host, port).sync().channel();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.w("connect","网络连接失败，请检查网络连接！");
				ConnectThread.toConnect = true;
				group.shutdownGracefully();
				b = null;
				return;
			}
			if(ch.isOpen()){
				AppContext.netContext.msgControlHelper.setChannel(ch);
			}
			ch.closeFuture().sync();
			Log.i("connect","网络连接关闭");
			}finally{
				group.shutdownGracefully();
			}
			 
	}
}
