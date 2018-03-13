package pers.android.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChangePswDao extends BaseDao {
	
	public boolean changePassword(int userId,String oldPsw,String newPsw){
		try {
			String sql = "select * from t_user where user_id ="+userId+" and user_password='"+oldPsw+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				return doChange(userId, newPsw);
			}
			else{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	private boolean doChange(int userId,String newPsw){
		try {
			String sql = "update t_user set user_password= '"+newPsw+"' where user_id ="+userId;
			stmt.executeUpdate(sql);			
			return true;			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
