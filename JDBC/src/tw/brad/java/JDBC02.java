package tw.brad.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC02 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/iii?user=root&password=root";
		//String url = "jdbc:資料庫品牌名:主機:PORT/資料庫?user=root&(&是分隔符號)password=root";
		String insert = "INSERT INTO CUST (name,tel,birthday)" +
						"VALUES (" +
						"'Brad','123','1999-01-02'"	+ 
						")";
		
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			stmt.execute(insert);//資料庫語法寫在""裏頭
			
			conn.close();//用戶端的關閉
			System.out.println("OK");//連接成功
		}catch(SQLException e) {
			System.out.println(e);
		}
	}

}
