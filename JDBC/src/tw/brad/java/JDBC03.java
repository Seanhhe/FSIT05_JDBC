package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC03 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii";
		//String url = "jdbc:資料庫品牌名:主機:PORT/資料庫?user=root&(&是分隔符號)password=root";
		
		String user = "root";//帳號密碼單獨寫出來，較易維護
		String password = "root";
		
		
		String insert = "INSERT INTO CUST (name,tel,birthday)" +
						"VALUES (" +
						"'Tony','321','1999-01-02'"	+ 
						")";
		
		try {
			Connection conn = DriverManager.getConnection(url, user , password);//帳號密碼單獨寫出來，較易維護
			Statement stmt = conn.createStatement();
			stmt.execute(insert);//資料庫語法寫在""裏頭
			
			conn.close();//用戶端的關閉
			System.out.println("OK");//連接成功
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

}
