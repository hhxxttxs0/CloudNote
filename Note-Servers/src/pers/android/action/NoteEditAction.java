package pers.android.action;

import com.mycloudnote.net.pojo.request.LoginRequest;
import com.mycloudnote.net.pojo.request.NoteEditRequest;
import com.mycloudnote.net.pojo.response.NoteEditResponse;
import com.mycloudnote.net.pojo.response.NoteEditResponse.NoteEditState;

import pers.android.dao.LoginDao;
import pers.android.dao.NoteEditDao;

public class NoteEditAction {
	private NoteEditRequest noteEditReq;
	private NoteEditDao dao ;
	private LoginDao lgDao;
	
	public NoteEditAction(NoteEditRequest noteEditReq){
		this.noteEditReq = noteEditReq;
		dao = new NoteEditDao();
		lgDao = new LoginDao();
	}
	public NoteEditResponse getNoteEditResponse(){
		NoteEditResponse resp = new NoteEditResponse();

		resp.setNoteEditState(dao.noteEdit(noteEditReq.getUserId(), noteEditReq.getNote()));
		resp.setNotes(lgDao.getNoteList(noteEditReq.getUserName()));
		return resp;
		
	}
}
