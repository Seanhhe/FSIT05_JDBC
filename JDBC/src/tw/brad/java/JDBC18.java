package tw.brad.java;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;


/*
 * 
 */

//01.
//public class JDBC18 {
//
//	public static void main(String[] args) {
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			DatabaseMetaData metadata = conn.getMetaData(); //meta是指管理資料的data
//			
//			//詢問是否支援資料庫connection
//			boolean isOK = metadata.supportsResultSetConcurrency(
//					ResultSet.TYPE_FORWARD_ONLY,
//					ResultSet.CONCUR_UPDATABLE);
//			System.out.println(isOK);
//			
//			String sql = "SELECT * FROM accounts WHERE id =2";
//			//Statement stmt = conn.createStatement(); //改成下方
//			Statement stmt = conn.createStatement(
//					ResultSet.TYPE_FORWARD_ONLY,
//					ResultSet.CONCUR_UPDATABLE);
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			rs.next();
//			System.out.println(rs.getString("account") + ":"
//					+ rs.getString("realname"));
//			
//			rs.updateString("realname", "Tony Chen");
//			rs.updateRow();
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
//
//}

//02.更新所有人的密碼成為6個1
public class JDBC18 {

	public static void main(String[] args) {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try (Connection conn =
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/iii", info);){
			DatabaseMetaData metadata = conn.getMetaData(); //meta是指管理資料的data
			
			//詢問是否支援資料庫connection
			boolean isOK = metadata.supportsResultSetConcurrency(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			System.out.println(isOK);
			
			String sql = "SELECT * FROM accounts WHERE id =2";
			//Statement stmt = conn.createStatement(); //改成下方
			Statement stmt = conn.createStatement(
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getString("account") + ":"
					+ rs.getString("realname"));
			
			rs.updateString("realname", "Tony Chen");
			rs.updateRow();
			
			//更新所有人的密碼成為6個1
			String sql2 = "SELECT * FROM accounts";
			PreparedStatement pstmt = conn.prepareStatement(sql2,
					ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rs.updateString("passwd", "111111");
				rs.updateRow();				
			}
			
			//新增Maryy Ho
			rs.moveToInsertRow();
			rs.updateString("account", "mary");
			rs.updateString("passwd", "222222");
			rs.updateString("realname", "Mary Ho");
			rs.insertRow();
			
			
			
			System.out.println(rs.getRow());//指標指到何處
			rs.beforeFirst();
			while(rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("account");
				System.out.println(id + ":" + name);
				rs.deleteRow();
			}
			
			//rs.last(); //只能砍到倒數第二筆
			//rs.afterLast();
			//rs.deleteRow();//不能砍掉剛剛新增的該筆資料，放進while迴圈裡
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
