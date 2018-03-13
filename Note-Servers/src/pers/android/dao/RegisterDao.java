package pers.android.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mycloudnote.net.pojo.response.Note;

public class RegisterDao extends BaseDao{
	
	public boolean doRegister(String userName,String password){
		try {

			String sql = "insert into t_user(user_name,user_password) values('"+userName
					+"','"+password+"')";			
			stmt.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public boolean isRegisterSuccessful(String userName,String password){
		try {

			String sql = "select * from t_user where user_name = '"+userName+"'";			
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return false;
			}
			else{
				return doRegister(userName,password);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}		
	}
}
