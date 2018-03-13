package pers.android.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NoteDeleteDao extends BaseDao{
	
	public boolean deleteNote(int noteId){
		try {
			String sql = "delete from t_note where note_id ="+noteId;
			stmt.executeUpdate(sql);			
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}
}
