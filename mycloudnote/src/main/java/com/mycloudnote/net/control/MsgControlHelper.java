package com.mycloudnote.net.control;

import android.util.Log;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.net.pojo.response.ChangePswResponse;
import com.mycloudnote.net.pojo.response.LoginResponse;
import com.mycloudnote.net.pojo.response.Note;
import com.mycloudnote.net.pojo.response.NoteDeleteResponse;
import com.mycloudnote.net.pojo.response.NoteEditResponse;
import com.mycloudnote.net.pojo.response.RegisterResponse;

import static com.mycloudnote.net.context.MsgConstant.CHANGEPASSWORD_FAILURE;
import static com.mycloudnote.net.context.MsgConstant.CHANGEPASSWORD_SUCCES;
import static com.mycloudnote.net.context.MsgConstant.DELETE_FAILURE;
import static com.mycloudnote.net.context.MsgConstant.DELETE_SUCCESS;
import static com.mycloudnote.net.context.MsgConstant.ERROR;
import static com.mycloudnote.net.context.MsgConstant.LOGIN_FAILURE;
import static com.mycloudnote.net.context.MsgConstant.LOGIN_SUCCESS;

public class MsgControlHelper {

	volatile ChannelHandlerContext channleCtx;
	volatile Channel ch;
	

	public void setChannelHandlerContext(ChannelHandlerContext ctx) {
		this.channleCtx = ctx;
	}

	public void setChannel(Channel ch) {
		this.ch = ch;

	}

	public Channel getChannel() {
		return ch;
	}

	public MsgControlHelper() {

	}
	/**
	 * 分发响应消息
	 * @param msg
	 */
	public void dispatchNetMsg(Object msg) {
		// TODO Auto-generated method stub
		if(msg instanceof LoginResponse){
			if(((LoginResponse)msg).getLoginState()==LoginResponse.LoginState.LoginSuccess){
				LoginResponse resp = ((LoginResponse) msg);
				AppContext.user.setUserName(resp.getUserName());
				AppContext.user.setUserId(resp.getUserId());
				AppContext.noteList = resp.getNotes();
				AppContext.loginHandler.sendEmptyMessage(LOGIN_SUCCESS);
			}
			else {
				AppContext.loginHandler.sendEmptyMessage(LOGIN_FAILURE);
			}
		}
		else if(msg instanceof RegisterResponse){
			if(((RegisterResponse)msg).getRegisterState()==RegisterResponse.RegisterState.RegisterSuccess){
				AppContext.registerHandler.sendEmptyMessage(1);
			}
			else {
				AppContext.registerHandler.sendEmptyMessage(2);
			}
		}
		else if(msg instanceof NoteEditResponse){
			if(((NoteEditResponse) msg).getNotes().size()!=0){
				AppContext.noteList = ((NoteEditResponse) msg).getNotes();
			};
			if(((NoteEditResponse)msg).getNoteEditState()== NoteEditResponse.NoteEditState.CreateSuccess){
				AppContext.editnoteHandler.sendEmptyMessage(1);


				AppContext.notelistHandler.sendEmptyMessage(1);
			}
			else if(((NoteEditResponse)msg).getNoteEditState()== NoteEditResponse.NoteEditState.CreateFailure){
				AppContext.editnoteHandler.sendEmptyMessage(2);
				AppContext.notelistHandler.sendEmptyMessage(1);
			}
			else if(((NoteEditResponse)msg).getNoteEditState()== NoteEditResponse.NoteEditState.EditSuccess){
				AppContext.editnoteHandler.sendEmptyMessage(3);
				AppContext.notelistHandler.sendEmptyMessage(1);
			}
			else if(((NoteEditResponse)msg).getNoteEditState()== NoteEditResponse.NoteEditState.EditFailure){
				AppContext.editnoteHandler.sendEmptyMessage(4);
				AppContext.notelistHandler.sendEmptyMessage(1);
			}
			else{
				AppContext.editnoteHandler.sendEmptyMessage(ERROR);
				AppContext.notelistHandler.sendEmptyMessage(ERROR);
			}

		}
		else if(msg instanceof NoteDeleteResponse){
			NoteDeleteResponse resp = (NoteDeleteResponse)msg;
			if(resp.getNoteDeleteState()== NoteDeleteResponse.NoteDeleteState.DeleteSuccess){
				AppContext.noteActivityHandler.sendEmptyMessage(DELETE_SUCCESS);
			}
			else{
				AppContext.noteActivityHandler.sendEmptyMessage(DELETE_FAILURE);
			}
		}
		else if(msg instanceof ChangePswResponse){
			ChangePswResponse resp = (ChangePswResponse)msg;
			if(resp.getChangePswState()== ChangePswResponse.ChangePswState.ChangePswSuccess){
				AppContext.changepswHandler.sendEmptyMessage(CHANGEPASSWORD_SUCCES);
			}
			else{
				AppContext.changepswHandler.sendEmptyMessage(CHANGEPASSWORD_FAILURE);
			}
		}
	}

	public boolean writeAndFlush(Object msg) {
		if (ch != null) {

			if (ch.isActive()) {
				ch.writeAndFlush(msg);
			} else {
				throw new RuntimeException("weiterAndFlush 发送数据出错");

			}
		}

		else {
			Log.e("connect","网络连接没有打开");
		}
		return true;
	}

}
