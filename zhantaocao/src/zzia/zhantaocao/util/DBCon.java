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
		 Connection con = null; //��ʾ���ݿ�����Ӷ���  
	        Statement stmt = null;  //��ʾ���ݿ�ĸ��²���  
	        ResultSet result = null; //��ʾ�������ݿ�Ĳ�ѯ���  
	        Class.forName("com.mysql.jdbc.Driver"); //1��ʹ��CLASS �������������  
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","111007204"); //2���������ݿ�  
	        stmt = con.createStatement(); //3��Statement �ӿ���Ҫͨ��Connection �ӿڽ���ʵ��������  
	        result = stmt.executeQuery("select * from workreport ;"); //ִ��SQL ��䣬��ѯ���ݿ�  
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
	                con.close(); // 4���ر����ݿ�  
	}
	
}
