package pers.android.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mycloudnote.net.pojo.response.Note;

public class LoginDao extends BaseDao{
	
	public ArrayList<Note> getNoteList(String userName){
		try {

			String sql = "select note_id,note_title,note_content,note_createtime from t_user,t_note "
					+ "where t_user.user_id = t_note.user_id and user_name = ?";
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1,userName);
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Note> notes = new ArrayList<Note>();
			while(rs.next()){
				Note n = new Note();
				n.setNoteId(rs.getInt(1));
				n.setTitle(rs.getString(2));
				n.setContent(rs.getString(3));
				n.setCreateTime(rs.getDate(4));
				notes.add(n);
			}
			return notes;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

		
	}
	public boolean isLoginSuccessful(String userName,String password){
		try {
			String sql = "select * from t_user where user_name = ? and user_password = ?";
			PreparedStatement pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1,userName);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}				
	}
	public int getNoteId(String userName){
		try {

			String sql = "select user_id from t_user where user_name = '"+userName+"'";			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
			else{
				return -1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
