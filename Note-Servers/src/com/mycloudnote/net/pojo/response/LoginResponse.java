package com.mycloudnote.net.pojo.response;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LoginState loginState;
	private int userId;
	private String userName;
	private ArrayList<Note> notes;
	
	public static enum LoginState {
		LoginSuccess, LoginFailure;
	}

	public LoginState getLoginState() {
		return loginState;
	}

	public void setLoginState(LoginState loginState) {
		this.loginState = loginState;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void setNotes(ArrayList<Note> note) {
		this.notes = note;
	}
	
}
