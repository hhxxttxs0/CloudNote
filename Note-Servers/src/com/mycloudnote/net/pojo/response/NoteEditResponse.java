package com.mycloudnote.net.pojo.response;

import java.io.Serializable;
import java.util.ArrayList;

public class NoteEditResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private NoteEditState noteEditState;
	private ArrayList<Note> notes;
	
	
	public static enum NoteEditState {
		EditSuccess, EditFailure, CreateSuccess, CreateFailure, ERROR;
	}
	
	public NoteEditState getNoteEditState() {
		return noteEditState;
	}

	public void setNoteEditState(NoteEditState noteEditState) {
		this.noteEditState = noteEditState;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}
	public void setNotes(ArrayList<Note> notes) {
		this.notes = notes;
	}
	
	
}
