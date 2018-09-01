package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

/*
 * 
 */

//01.
//public class JDBC14 {
//
//	public static void main(String[] args) {
//		
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS nums FROM gifts");
//			rs.next();
//			String nums = rs.getString("nums");
//			System.out.println(nums);//得到213
//			
//			rs = stmt.executeQuery("SELECT name,feature FROM gifts");
//			
//		}catch(Exception e) {
//			
//		}
//	}
//}

// 02.
//public class JDBC14 {
//
//	public static void main(String[] args) {
//		
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS nums FROM gifts");
//			rs.next();
//			String nums = rs.getString("nums");
//			System.out.println(nums);//得到213
//			
//			rs = stmt.executeQuery("SELECT name,feature FROM gifts");
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String name = rs.getString("name");
//				System.out.println(id + ":" + name);
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
//}


//03.加入分頁功能
//public class JDBC14 {
//
//	public static void main(String[] args) {
//		
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS nums FROM gifts");
//			rs.next();
//			String nums = rs.getString("nums");
//			System.out.println(nums);//得到213
//			
//			int rpp = 10; // row per page
//			int page = 1; // review page 1
//			
//			
//			rs = stmt.executeQuery(
//					//"SELECT name,feature FROM gifts LIMIT 0,10");
//					"SELECT name,feature FROM gifts LIMIT 10,10");
//			while (rs.next()) {
//				String id = rs.getString("id");
//				String name = rs.getString("name");
//				System.out.println(id + ":" + name);
//			}
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
//}

//04.分頁功能提供指定頁面功能
public class JDBC14 {

	public static void main(String[] args) {
		
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try (Connection conn =
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/iii", info);){
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS nums FROM gifts");
			rs.next();
			String nums = rs.getString("nums");
			System.out.println(nums);//得到213
			
			int rpp = 10; // row per page
			int page = 1; // review page 1
			
			
			//指定分頁功能
			int start = (page-1)*rpp;
			
			rs = stmt.executeQuery(
					//"SELECT name,feature FROM gifts LIMIT 0,10");
					"SELECT name,feature FROM gifts LIMIT" + start + "," + rpp);
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				System.out.println(id + ":" + name);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
}
