package tw.brad.java;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



/*
 * JSON格式的Stream
 * 
 * 參考資料：http://bunkera.pixnet.net/blog/post/24326115-mysql%E8%AA%BF%E6%95%B4%E6%88%90%E5%85%A8utf-8%E8%AA%9E%E7%B3%BB
 * 參考資料：http://www.vixual.net/blog/archives/310
 */

//01.主要格式架構
//public class JDBC13 {
//	public static void main(String[] args) {
//	
	//把資料撈回來放入字串	
//	String source = fetchOpendata();
//		if(source != null) {
//			toMyDB(source);
//		}else {
//			System.out.println("no data");
//		}
//	}
//	//擷取資料
//	static String fetchOpendata() {
//		return "";
//	}
//	
//	//寫入資料庫
//	static void toMyDB(String json) {
//		
//	}
//}


//02.完成解析JSON至資料庫界接的準備
//public class JDBC13 {
//	public static void main(String[] args) {
//		String source = fetchOpendata();//把資料撈回來放入字串
//		if(source != null) {
//			toMyDB(source);
//		}else {
//			System.out.println("no data");
//		}
//	}
//	
//	//自行設計的方法
//	static String fetchOpendata() {
//		String ret = null;
//		try {
//			URL url = new URL("http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx");//設定URL為擷取目標
//			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//			conn.connect();
//			
//			
//			
//			BufferedReader reader = 
//					new BufferedReader(
//							new InputStreamReader(conn.getInputStream()));
//			String line; StringBuffer sb = new StringBuffer();
//			//適用連在一起的json內容：reader.readLine()
//			while ((line = reader.readLine()) != null) {//為了農委會的json的換行內容而寫了while迴圈判斷
//				sb.append(line);
//			}
//			
//			reader.close();
//			
//			ret = sb.toString();
//			
//		} catch (Exception e) {
//			
//		}
//		return ret;
//	}
//	
//	static void toMyDB(String json) {
//		JSONArray root = new JSONArray(json);
//		//System.out.println(root.length());//解析json共有幾筆資料213筆
//		for (int i=0; i<root.length(); i++) {
//			JSONObject row = root.getJSONObject(i);
//			String name = row.getString("Name");
//			String feature = row.getString("Feature");
//			String place = row.getString("SalePlace");
//			String imgurl = row.getString("Column1");
//			System.out.println(name + ":" + feature + ":" + place + ":" + imgurl);//印出解析內容
//			
//		}
//	}
//}


//03.資料庫連線
public class JDBC13 {
	public static void main(String[] args) {
		String source = fetchOpendata();//把資料撈回來放入字串
		if(source != null) {
			toMyDB(source);
		}else {
			System.out.println("no data");
		}
	}

	static String fetchOpendata() {
		String ret = null;
		try {
			URL url = new URL("http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx");//設定URL為擷取目標
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			
			
			
			BufferedReader reader = 
					new BufferedReader(
							new InputStreamReader(conn.getInputStream()));
			String line; StringBuffer sb = new StringBuffer();
			//適用連在一起的json內容：reader.readLine()
			while ((line = reader.readLine()) != null) {//為了農委會的json的換行內容而寫了while迴圈判斷
				sb.append(line);
			}
			
			reader.close();
			
			ret = sb.toString();
			
		} catch (Exception e) {
			
		}
		return ret;
	}
	
	static void toMyDB(String json) {
		Properties info = new Properties();
		info.setProperty("user", "root");
		info.setProperty("password", "root");
		try (Connection conn =
			DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/iii", info);){
			
			//把資料庫ID歸零：https://stackoverflow.com/questions/8923114/how-to-reset-auto-increment-in-mysql#8923132
			Statement stmt = conn.createStatement();
			stmt.execute("alter table gifts AUTO_INCREMENT = 1");
			
			
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO gifts (name, feature, place, imgurl) VALUES (?,?,?,?)");
			
						
			JSONArray root = new JSONArray(json);
			//System.out.println(root.length());//解析農委會特色小吃json共有幾筆資料213筆
			for (int i=0; i<root.length(); i++) {
				try {
					JSONObject row = root.getJSONObject(i);
					String name = row.getString("Name");
					String feature = row.getString("Feature");
					String place = row.getString("SalePlace");
					String imgurl = row.getString("Column1");
					//System.out.println(name + ":" + feature + ":" + place + ":" + imgurl);//印出解析內容
					
					pstmt.setString(1, name);
					pstmt.setString(2, feature);
					pstmt.setString(3, place);
					pstmt.setString(4, imgurl);
					
				}catch (JSONException je) {
					//印出JSON例外
					System.out.println("json error" + i);
				}

			}
			System.out.println("OK");
			
		} catch (Exception e) {//catch (SQLException e)改成(Exception e)
			//e.printStackTrace();
			System.out.println(e.toString());
		}
		
		
		}
	}
