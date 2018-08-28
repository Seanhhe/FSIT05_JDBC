package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC06 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		//String url = "jdbc:資料庫品牌名:主機:PORT/資料庫?user=root&(&是分隔符號)password=root";
		
		//String user = "root";//帳號密碼單獨寫出來，較易維護
		//String password = "root";
		
		Properties prop = new Properties();//java.util class Properties的setProperty(String key,String value)與getProperty
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		String del = "DELETE FROM CUST WHERE ID=1";
		//String del = "DELETE FROM CUST";//全刪資料表
		try (Connection conn = DriverManager.getConnection(url, prop);){
			//放入try ()中
			Statement stmt = conn.createStatement();
			stmt.execute(del);//資料庫語法寫在""裏頭
			
			conn.close();//用戶端的關閉
			System.out.println("OK");//連接成功
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

}
