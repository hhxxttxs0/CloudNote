package pers.android.clientTest;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycloudnote.net.pojo.request.*;
import com.mycloudnote.net.pojo.response.*;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class ClientHandler extends ChannelHandlerAdapter{
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception{
		super.channelActive(ctx);

		LoginRequest req = new LoginRequest();
		req.setUserName("chenjiawei");
		req.setPassword("123456");
		
//		RegisterRequest req = new RegisterRequest();
//		req.setUserName("ligoude");
//		req.setPassword("123456");
		
//		NoteEditRequest req = new NoteEditRequest();
//		req.setUserId(1);
//		req.setUserName("chenjiawei");
//		Note note = new Note();
////		note.setNoteId(1);
//		note.setTitle("forthNote");
//		note.setContent("one note two note three note..");
//		note.setCreateTime(new Date());
//		req.setNote(note);
		
//		NoteDeleteRequest req = new NoteDeleteRequest();
//		Note note = new Note();
//		note.setNoteId(4);
//		req.setUserId(1);
//		req.setNote(note);
		
//		ChangePswRequest req = new ChangePswRequest();
//		req.setUserId(1);
//		req.setOldPsw("666666");
//		req.setNewPsw("123456");
		ctx.writeAndFlush(req);
		System.out.println("channelActive");
	}
	public static Date getNowDate() {
		   Date currentTime = new Date();
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   String dateString = formatter.format(currentTime);
		   Date currentTime_2 = null;
		try {
			currentTime_2 = formatter.parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return currentTime_2;
		}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {  
		  
//        System.out.println("---------------channelRead have received : " + msg.toString() + "  ");
//        System.out.println(((LoginResponse) msg).getLoginState());;
		if(msg instanceof LoginResponse){
			System.out.println("Recive loginreq");
			LoginResponse req = (LoginResponse)msg;
			System.out.println("userId "+req.getUserId());
			System.out.println(req.getNotes().get(0).getCreateTime().getDate());
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("fomatDate :"+sdf.format(req.getNotes().get(0).getCreateTime()));
			
			
		}
		else if(msg instanceof RegisterResponse){
			RegisterResponse req = (RegisterResponse)msg;
			System.out.println("Recive registerreq");

		}
		else if(msg instanceof NoteEditResponse){
			NoteEditResponse req = (NoteEditResponse)msg;
			System.out.println("NoteEdit");
		}
		else if(msg instanceof ChangePswResponse){
			ChangePswResponse resp = (ChangePswResponse)msg;
			System.out.println("state "+resp.getChangePswState().toString());
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
