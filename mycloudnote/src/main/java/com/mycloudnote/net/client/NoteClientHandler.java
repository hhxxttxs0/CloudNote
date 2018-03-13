package com.mycloudnote.net.client;

import android.util.Log;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import com.mycloudnote.net.context.AppContext;

public class NoteClientHandler extends ChannelHandlerAdapter{
	private String TAG = "ClientHandler";
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception{
		super.channelActive(ctx);
		AppContext.netContext.msgControlHelper.setChannelHandlerContext(ctx);
		System.out.println("channelActive");
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
		  
        System.out.println("---------------channelRead have received : " + msg.toString() + "  ");
        AppContext.netContext.receiveNetMsg(msg);
    }
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		Log.i(TAG, "发生异常--" + cause.getCause().toString());
		ctx.close(); //
	}
}
