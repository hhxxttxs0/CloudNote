package com.mycloudnote.net.pojo.response;

import java.io.Serializable;

public class RegisterResponse implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private RegisterState registerState; // 注册状态
	private String userName;

	public static enum RegisterState {
		RegisterSuccess,RegisterFailure,UserExists;
	}

	public RegisterState getRegisterState() {
		return registerState;
	}
	public void setRegisterState(RegisterState registerState) {
		this.registerState = registerState;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}


}
