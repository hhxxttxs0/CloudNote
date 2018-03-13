package com.mycloudnote.net.pojo.request;

import java.io.Serializable;

import com.mycloudnote.net.pojo.response.Note;

public class NoteDeleteRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private Note note;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Note getNote() {
		return note;
	}
	public void setNote(Note note) {
		this.note = note;
	}
	
}
