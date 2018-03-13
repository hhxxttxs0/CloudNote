package com.mycloudnote.net.pojo.request;

import com.mycloudnote.net.pojo.response.Note;
import java.io.Serializable;
import java.util.ArrayList;

public class NoteEditRequest implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private String userName;
	private Note note;

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

	public Note getNote() {
		return note;
	}

	public void setNote(Note note) {
		this.note = note;
	}
}
