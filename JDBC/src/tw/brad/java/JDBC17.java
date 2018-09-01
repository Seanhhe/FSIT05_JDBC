package tw.brad.java;
/*
 * 檢查帳號密碼登入，並創出會員物件
 */
//public class JDBC17 {
//
//	public static void main(String[] args) {
//		
//	}
//	
//}
//
////會員物件
//class Member {
//	String account, realname;
//	Member(String account, String realname){
//		this.account = account;
//		this.realname = realname;
//	}
//}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

// 02.架構
//public class JDBC17 {
//
//	public static void main(String[] args) {
//		
//		String account = "", passwd="";
//		
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			Member member = null;
//			if ((member = checkMember(account,passwd,conn)) != null) {
//				System.out.println("Welcome" + member.realname);
//			}else {
//				System.out.println("Login Fail");
//			}
//		}catch(Exception e) {
//			System.out.println("Server Busy");//假裝很忙，其實是出錯
//		}
//		
//	}
//	
//	//檢查帳密
//	static Member checkMember(String account, String passwd, Connection conn) {
//		return null;
//	}
//	
//}
//
////會員物件
//class Member {
//	String account, realname;
//	Member(String account, String realname){
//		this.account = account;
//		this.realname = realname;
//	}
//}

// 03.檢查帳密功能加入
public class JDBC17 {

	public static void main(String[] args) {
		
		String account = "", passwd="";
		
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try (Connection conn =
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/iii", info);){
			Member member = null;
			if ((member = checkMember(account,passwd,conn)) != null) {
				System.out.println("Welcome" + member.realname);
			}else {
				System.out.println("Login Fail");
			}
		}catch(Exception e) {
			System.out.println("Server Busy");//假裝很忙，其實是出錯
		}
		
	}
	
	//檢查帳密(明碼作法，現不流行)
	static Member checkMember(String account, String passwd, Connection conn) 
			throws SQLException {
			
		String sql = "SELECT * FROM accounts WHERE account=? and passwd=?";//現行做法：去掉and passwd=?再與密碼比對
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, account);
		pstmt.setString(2, passwd);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return new Member(rs.getString("account"),
					rs.getString("realname"));
		}else{
			//
			return null;
		}
	}
	
}

//會員物件
class Member {
	String account, realname;
	Member(String account, String realname){
		this.account = account;
		this.realname = realname;
	}
}