package com.mycloudnote.net.pojo.response;

import java.io.Serializable;

public class ChangePswResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private ChangePswState changePswState;
	public static enum ChangePswState {
		ChangePswSuccess, ChangePswFailure;
	}
	public ChangePswState getChangePswState() {
		return changePswState;
	}
	public void setChangePswState(ChangePswState changePswState) {
		this.changePswState = changePswState;
	}

}
