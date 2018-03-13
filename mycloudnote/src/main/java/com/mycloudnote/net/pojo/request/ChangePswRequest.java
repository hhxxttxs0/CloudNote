package com.mycloudnote.net.pojo.request;

import java.io.Serializable;

public class ChangePswRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String oldPsw;
	private String newPsw;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getOldPsw() {
		return oldPsw;
	}
	public void setOldPsw(String oldPsw) {
		this.oldPsw = oldPsw;
	}
	public String getNewPsw() {
		return newPsw;
	}
	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}



}
