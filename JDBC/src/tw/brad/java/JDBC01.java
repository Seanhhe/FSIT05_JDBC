package tw.brad.java;
/*
 * 01.要先在Package右鍵>NEW>Build Path>Configure Build Path
 * 02.然後在Libraries標籤，點選classPath後，按下Add External JARs
 * 03.把路徑指向MySQL connector的驅動程式資料夾，然後確定。
 * 
 */
import java.lang.reflect.Method;//反射

public class JDBC01 {

	public static void main(String[] args) {
		
//		test01		
//		String str1 = new String();
//		String str2 = "";
//		
//		Class class1 = str1.getClass();
//		System.out.println(class1.getName());
//		Class class2 = class1.getSuperclass();
//		System.out.println(class2.getName());
//		
//		Method[] methods = class1.getDeclaredMethods();
//		for (Method method : methods) {
//			System.out.println(method.getName());
//		}
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//參考文件網址：https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-reference-class-name.html
			//只要此驅動程式有執行載入此程式，後續就不用再每次寫Class.forName("com.mysql.jdbc.Driver")
			System.out.println("OK");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Not Found");;
		}
	}

}
