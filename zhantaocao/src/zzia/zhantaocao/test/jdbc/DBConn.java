package zzia.zhantaocao.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DBConn {
	public static void main(String[] args) throws Exception {
		   Connection con = null; //��ʾ���ݿ�����Ӷ���  
	        Statement stmt = null;  //��ʾ���ݿ�ĸ��²���  
	        ResultSet result = null; //��ʾ�������ݿ�Ĳ�ѯ���  
	        Class.forName("com.mysql.jdbc.Driver"); //1��ʹ��CLASS �������������  
	        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","111007204"); //2���������ݿ�  
	        stmt = con.createStatement(); //3��Statement �ӿ���Ҫͨ��Connection �ӿڽ���ʵ��������  
	        result = stmt.executeQuery("select * from workreport ;"); //ִ��SQL ��䣬��ѯ���ݿ�  
	        ResultSetMetaData rsmd = result.getMetaData() ;
	        for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				System.out.println(rsmd.getColumnName(i));
			}
	        while (result.next()){  
	            String name = result.getString("wr_desc");  
	            String address = result.getString("wr_time");  
	        }  
	                result.close();  
	                con.close(); // 4���ر����ݿ�  
	}
}
