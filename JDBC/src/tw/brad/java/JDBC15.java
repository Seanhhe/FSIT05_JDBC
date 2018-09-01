package tw.brad.java;
/*
 * 產生輸出JSON格式()
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;

//01.
//public class JDBC15 {
//
//	public static void main(String[] args) {
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			String sql = "SELECT * FROM gifts";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			
//		}catch(Exception e) {
//			
//		}
//	}
//
//}

//02.
//public class JDBC15 {
//
//	public static void main(String[] args) {
//		Properties info = new Properties();
//		info.setProperty("user", "root");
//		info.setProperty("password", "root");
//		try (Connection conn =
//			DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/iii", info);){
//			String sql = "SELECT * FROM gifts";
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//			ResultSet rs = pstmt.executeQuery();
//			
//			JSONStringer js = new JSONStringer();
//			JSONWriter jw = js.array();
//				jw.object();
//					jw.key("k1").value("v1");
//					jw.key("k2").value("v2");
//				jw.endObject();
//				
//				jw.object();
//					jw.key("k1").value("v3");
//					jw.key("k2").value("v4");
//				jw.endObject();
//					
//			jw.endArray();
//			//JSONWriter jw = js.object();
//			//jw.key("key1").value("value1");
//			//jw.endObject();
//			System.out.println(jw);
//			
//		}catch(Exception e) {
//			System.out.println(e);
//		}
//	}
//
//}


//03.加入迴圈
public class JDBC15 {

	public static void main(String[] args) {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try (Connection conn =
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/iii", info);){
			String sql = "SELECT * FROM giftsLIMIT 0,4";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			JSONStringer js = new JSONStringer();
			JSONWriter jw = js.array();
			while(rs.next()) {
				String name = rs.getString("name");
				String feature = rs.getString("feature");
				String img = rs.getString("imgurl");
			
			
				jw.object();
					jw.key("名稱").value("v1");
					jw.key("特色").value("v2");
					//jw.key("圖片").value(img);
				jw.endObject();
				
			}	
					
			jw.endArray();
			//JSONWriter jw = js.object();
			//jw.key("key1").value("value1");
			//jw.endObject();
			System.out.println(jw);
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}