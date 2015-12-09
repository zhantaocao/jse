package zzia.zhantaocao.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Properties;

public class DBCon {
	
	private static  String driver ;
	private static  String url ;
	private static  String username ;
	private static  String password ;
	
	private Connection con ; 
	private Statement stmt ;
	
	static{
		Properties prop =  new  Properties();    
        InputStream in = Object. class .getResourceAsStream( "/zzia/zhantaocao/conf/database.properties" );    
         try  {    
            prop.load(in);    
            driver = prop.getProperty("driver") ;
            url = prop.getProperty("url") ;
            username = prop.getProperty("username") ;
            password = prop.getProperty("password") ;
        }  catch  (IOException e) {    
            e.printStackTrace();    
        }    
	}
	
	public Statement connDB() throws Exception{
		Class.forName(driver);
		con = DriverManager.getConnection(url, username, password);
		stmt = con.createStatement() ;
		return stmt ;
	}
	
	public void closeConnDB() throws Exception{
		if(stmt!=null){
			stmt.close() ;
		}
		if(con!=null){
			con.close() ;
		}
	}
	
	public static void main(String[] args) throws Exception {
		 Connection con = null; //表示数据库的连接对象  
	        Statement stmt = null;  //表示数据库的更新操作  
	        ResultSet result = null; //表示接收数据库的查询结果  
	        Class.forName("com.mysql.jdbc.Driver"); //1、使用CLASS 类加载驱动程序  
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","111007204"); //2、连接数据库  
	        stmt = con.createStatement(); //3、Statement 接口需要通过Connection 接口进行实例化操作  
	        result = stmt.executeQuery("select * from workreport ;"); //执行SQL 语句，查询数据库  
//	        Field[] fields = result.getClass().getFields() ;
//	        for (Field field : fields) {
//				System.out.println(field.toString());
//			}
	        ResultSetMetaData rsmd = result.getMetaData() ;
	        for (int i = 1; i < rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i));
			}
	        while (result.next()){  
	            String name = result.getString("wr_desc");  
	            String address = result.getString("wr_time");  
	            System.out.println(result);
//	            System.out.println(name+address);  
	        }  
	                result.close();  
	                con.close(); // 4、关闭数据库  
	}
	
}
