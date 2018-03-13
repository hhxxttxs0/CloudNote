package com.mycloudnote.net.pojo.response;

import java.io.Serializable;

public class NoteDeleteResponse implements Serializable {
	
	private NoteDeleteState noteDeleteState;
	public static enum NoteDeleteState {
		DeleteSuccess, DeleteFailure;
	}
	public NoteDeleteState getNoteDeleteState() {
		return noteDeleteState;
	}
	public void setNoteDeleteState(NoteDeleteState noteDeleteState) {
		this.noteDeleteState = noteDeleteState;
	}
		
}
