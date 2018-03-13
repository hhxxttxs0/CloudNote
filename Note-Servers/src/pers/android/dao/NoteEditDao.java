package pers.android.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mycloudnote.net.pojo.response.Note;
import com.mycloudnote.net.pojo.response.NoteEditResponse.NoteEditState;

public class NoteEditDao extends BaseDao{
	
	public NoteEditState noteEdit(int userId,Note note){
		try {
			//检查该笔记是否已存在
			String sql = "select * from t_note where user_id ='"+userId
					+"' and note_id ='"+note.getNoteId()+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return doUpdateNote(userId,note);
			}
			else{
				return doInsertNote(userId,note);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return NoteEditState.ERROR;
		}
		
	}
	public NoteEditState doInsertNote(int userId,Note note){
		try {

			String sql = "insert into t_note(user_id,note_title,note_content,note_createtime) "
					+ "values('"+userId+"','"+note.getTitle()+"','"
					+ note.getContent()+"','"+getFormatTime(note.getCreateTime())+"')";
			stmt.executeUpdate(sql);
			return NoteEditState.CreateSuccess;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return NoteEditState.CreateFailure;
		}
		
	}
	public NoteEditState doUpdateNote(int userId,Note note){
		try {

			String sql = "update t_note set note_title='"+note.getTitle()+"',note_content='"
					+note.getContent()+"' where user_id='"+userId+"' and note_id='"+note.getNoteId()+"'";			
			stmt.executeUpdate(sql);
			return NoteEditState.EditSuccess;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return NoteEditState.EditFailure;
		}
		
	}
	private String getFormatTime(Date date){
		String value = null;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		value = df.format(date);
		return value;
	}
}
