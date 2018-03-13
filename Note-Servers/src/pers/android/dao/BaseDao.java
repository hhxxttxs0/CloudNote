package pers.android.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	private static final String URL = "jdbc:mysql://localhost:3306/mycloudnotedb?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "123456";

	private static Connection conn;
	protected static Statement stmt = null;
//	PreparedStatement pstmt = null;
	// 只需要执行一次  
    static{  
        try {  
            System.out.println("注册数据库驱动......");  
            Class.forName("com.mysql.jdbc.Driver");  
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {  
            throw new RuntimeException("注册数据库驱动出现异常:"+e.getMessage());  
        } catch (SQLException e) {
			// TODO Auto-generated catch block
        	System.out.println("获取数据库连接失败");
			e.printStackTrace();
		}  
    } 
	protected Statement getStatement() {
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;		
	}
//	protected PreparedStatement getPreparedStatement() {
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return stmt;		
//	}
	protected Connection getConnection(){
		return conn;
	}
}
