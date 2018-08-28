package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC08 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		//String url = "jdbc:資料庫品牌名:主機:PORT/資料庫?user=root&(&是分隔符號)password=root";
		
				
		Properties prop = new Properties();//java.util class Properties的setProperty(String key,String value)與getProperty
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		//String del = "DELETE FROM CUST WHERE ID=1";
		//String del = "DELETE FROM CUST";//全刪資料表
		
		String query = "SELECT name,id as `no`,tel,birthday FROM cust;";
		
		try (Connection conn = DriverManager.getConnection(url, prop);){
			//放入try ()中
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);//資料庫語法寫在""裏頭
			
			while (rs.next()){
				String f1 = rs.getString("no");//"columLable"需改成與SQL語法中 AS `no`相同
				String f2 = rs.getString("name");
				String f3 = rs.getString("tel");
				String f4 = rs.getString("birthday");
				
				System.out.println(f1 + ":" + f2 +":" + f3 + ":" + f4);
			}
			
			conn.close();//用戶端的關閉
			System.out.println("OK");//連接成功
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

}
