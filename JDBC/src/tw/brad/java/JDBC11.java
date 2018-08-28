package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC11 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		//String url = "jdbc:資料庫品牌名:主機:PORT/資料庫?user=root&(&是分隔符號)password=root";
		
				
		Properties prop = new Properties();//java.util class Properties的setProperty(String key,String value)與getProperty
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		//String del = "DELETE FROM CUST WHERE ID=1";
		//String del = "DELETE FROM CUST";//全刪資料表
		
		String query = "UPDATE cust set tel=? where name like ?";//避免隱碼攻擊
		
		try (Connection conn = DriverManager.getConnection(url, prop);){
			
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "0912765432");
			pstmt.setString(2, "Brad%");
			
			int result = pstmt.executeUpdate();
			System.out.println("OK:" + result);
			
			System.out.println("OK");//連接成功
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

}
