package pers.android.action;

import com.mycloudnote.net.pojo.request.NoteDeleteRequest;
import com.mycloudnote.net.pojo.response.NoteDeleteResponse;
import com.mycloudnote.net.pojo.response.NoteDeleteResponse.NoteDeleteState;

import pers.android.dao.NoteDeleteDao;

public class NoteDeleteAction {
	private NoteDeleteRequest noteDeleteReq;
	private NoteDeleteDao dao;
	
	public NoteDeleteAction(NoteDeleteRequest noteDeleteReq){
		this.noteDeleteReq = noteDeleteReq;
		dao = new NoteDeleteDao();
	}
	public NoteDeleteResponse getNoteDeleteResponse(){
		NoteDeleteResponse resp = new NoteDeleteResponse();
		if(dao.deleteNote(noteDeleteReq.getNote().getNoteId())){
			resp.setNoteDeleteState(NoteDeleteState.DeleteSuccess);
		}
		else{
			resp.setNoteDeleteState(NoteDeleteState.DeleteFailure);
		}
		return resp;
		
	}
}
