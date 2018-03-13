package pers.android.handler;

import java.net.InetSocketAddress;

import com.mycloudnote.net.pojo.request.ChangePswRequest;
import com.mycloudnote.net.pojo.request.LoginRequest;
import com.mycloudnote.net.pojo.request.NoteDeleteRequest;
import com.mycloudnote.net.pojo.request.NoteEditRequest;
import com.mycloudnote.net.pojo.request.RegisterRequest;
import com.mycloudnote.net.pojo.response.ChangePswResponse;
import com.mycloudnote.net.pojo.response.LoginResponse;
import com.mycloudnote.net.pojo.response.NoteDeleteResponse;
import com.mycloudnote.net.pojo.response.NoteEditResponse;
import com.mycloudnote.net.pojo.response.RegisterResponse;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import pers.android.action.ChangePswAction;
import pers.android.action.LoginAction;
import pers.android.action.NoteDeleteAction;
import pers.android.action.NoteEditAction;
import pers.android.action.RegisterAction;

public class NoteServerHandler extends ChannelHandlerAdapter{
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

		System.out.println("1");
		InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
		String ip = remoteAddress.getAddress().getHostAddress();
		int port = remoteAddress.getPort();
		System.out.println("ip:"+ip+" port:"+port);
		if(msg instanceof LoginRequest){
			System.out.println("Recive loginreq");
			LoginRequest req = (LoginRequest)msg;
			LoginAction action = new LoginAction(req);
			LoginResponse builder = action.getLoginResponse();
			ctx.writeAndFlush(builder);
		}
		else if(msg instanceof RegisterRequest){
			System.out.println("Recive registerreq");
			RegisterRequest req = (RegisterRequest)msg;
			RegisterAction action = new RegisterAction(req);
			RegisterResponse builder = action.getRegisterResponse();
			ctx.writeAndFlush(builder);
		}
		else if(msg instanceof NoteEditRequest){
			System.out.println("Recive editnoteeq");
			NoteEditRequest req = (NoteEditRequest)msg;
			NoteEditAction action = new NoteEditAction(req);
			NoteEditResponse builder = action.getNoteEditResponse();
			ctx.writeAndFlush(builder);
		}
		else if(msg instanceof NoteDeleteRequest){
			System.out.println("Recive NoteDelete ");
			NoteDeleteRequest req = (NoteDeleteRequest)msg;
			NoteDeleteAction action = new NoteDeleteAction(req);
			NoteDeleteResponse builder = action.getNoteDeleteResponse();
			ctx.writeAndFlush(builder);
		}
		else if(msg instanceof ChangePswRequest){
			System.out.println(" recieve changepsw");
			ChangePswRequest req = (ChangePswRequest)msg;
			ChangePswAction action = new ChangePswAction(req);
			ChangePswResponse builder = action.getChangePswResponse();
			ctx.writeAndFlush(builder);
		}
	}
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close(); //
	}
}
