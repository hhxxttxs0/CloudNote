package com.mycloudnote.net.action;

import com.mycloudnote.net.context.AppContext;
import com.mycloudnote.net.pojo.request.ChangePswRequest;
import com.mycloudnote.net.pojo.request.LoginRequest;
import com.mycloudnote.net.pojo.request.NoteDeleteRequest;
import com.mycloudnote.net.pojo.request.NoteEditRequest;
import com.mycloudnote.net.pojo.request.RegisterRequest;
import com.mycloudnote.net.pojo.response.Note;


public class InterAction {
	
	
	public void requestRegister(String userName,String password){
		RegisterRequest req = new RegisterRequest();
		req.setUserName(userName);
		req.setPassword(password);
		AppContext.netContext.sendNetMsg(req);
	}
	public void requestLogin(String userName,String password){
		LoginRequest req = new LoginRequest();
		req.setUserName(userName);
		req.setPassword(password);
		AppContext.netContext.sendNetMsg(req);
	}
	public void requestEditNote(int userId, String userName, Note note){
		NoteEditRequest req = new NoteEditRequest();
		req.setUserId(userId);
		req.setUserName(userName);
		req.setNote(note);
		AppContext.netContext.sendNetMsg(req);
	}
	public void requestDeleteNote(Note note){
		NoteDeleteRequest req = new NoteDeleteRequest();
		req.setNote(note);
		AppContext.netContext.sendNetMsg(req);
	}
	public void requestChangePassword(int userId,String oldPsw, String newPsw){
		ChangePswRequest req = new ChangePswRequest();
		req.setUserId(userId);
		req.setOldPsw(oldPsw);
		req.setNewPsw(newPsw);
		AppContext.netContext.sendNetMsg(req);
	}
}
