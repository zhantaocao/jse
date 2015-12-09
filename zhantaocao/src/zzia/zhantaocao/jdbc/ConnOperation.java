package zzia.zhantaocao.jdbc;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import zzia.zhantaocao.bean.User;
import zzia.zhantaocao.util.DBCon;
import zzia.zhantaocao.util.ObjectMapping;


public class ConnOperation {
	
	private DBCon dbCon ;
	
	//Ӧ���ж�̬����Ϊ���
	public List<Object> queryAllUser(String sql,Object object){
		dbCon = new DBCon() ;
		return null ;
	}
	
	public List<Object> MappingResult(ResultSet rs,Object object){
		
		return null ;
	}
	
	
	public static void main(String[] args) throws Exception {
		Connection con = null; //��ʾ���ݿ�����Ӷ���  
        Statement stmt = null;  //��ʾ���ݿ�ĸ��²���  
        ResultSet result = null; //��ʾ�������ݿ�Ĳ�ѯ���  
        Class.forName("com.mysql.jdbc.Driver"); //1��ʹ��CLASS �������������  
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","111007204"); //2���������ݿ�  
        stmt = con.createStatement(); //3��Statement �ӿ���Ҫͨ��Connection �ӿڽ���ʵ��������  
        result = stmt.executeQuery("select * from user"); //ִ��SQL ��䣬��ѯ���ݿ�  
//        Field[] fields = result.getClass().getFields() ;
//        for (Field field : fields) {
//			System.out.println(field.toString());
//		}
//        ResultSetMetaData rsmd = result.getMetaData() ;
//        for (int i = 1; i < rsmd.getColumnCount(); i++) {
//			System.out.println(rsmd.getColumnName(i));
//		}
		Class cls;
		cls = Class.forName("zzia.zhantaocao.bean.User");//class_path Ϊ��·������������������java.lang.String
		Object classObj = cls.newInstance();
		ResultSetMetaData rsmd = result.getMetaData() ;
		Map<String, Object> columnMap = new HashMap<String, Object>() ;
//		for (int i = 1; i <= rsmd.getColumnCount(); i++) {
//			System.out.println(rsmd.getColumnName(i));
//			System.out.println("coluType:"+rsmd.getColumnTypeName(i));
//			columnMap.put(rsmd.getColumnName(i), "rsmd.getColumnType(i)") ;
////			ObjectMapping.invokeSet(classObj, rsmd.getColumnName(i), "12") ;
//		}
		System.out.println(columnMap.toString());
		 while (result.next()){  
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					System.out.println(rsmd.getColumnName(i));
					System.out.println("coluType:"+rsmd.getColumnTypeName(i));
					Object object = result.getObject(i) ;
//					Object object = "sada" ;
					String str =object.getClass().getName() ;
					System.out.println("as--"+object.getClass().getName()+"-haha");
					System.out.println();
//					columnMap.put(rsmd.getColumnName(i), "rsmd.getColumnType(i)") ;
					ObjectMapping.invokeSet(classObj, rsmd.getColumnName(i), object) ;
				}
//	            String name = result.getString("wr_desc");  
//	            String address = result.getString("wr_time");  
//	            System.out.println(object.toString());
//	            System.out.println(object2.toString());
//	            System.out.println(name+address);  
	        }  
		 System.out.println(classObj.toString());
//		Field fieldlist[] = cls.getDeclaredFields(); 
//	      for (int i = 0; i < fieldlist.length; i++) { 
//	       Field fld = fieldlist[i]; 
//	       System.out.println("name = " + fld.getName()); 
//	       System.out.println("decl class = " + fld.getDeclaringClass()); 
//	       System.out.println("type = " + fld.getType()); 
//	       int mod = fld.getModifiers(); 
//	       System.out.println("modifiers = " + Modifier.toString(mod)); 
//	       System.out.println("-----"); 
//	      } 
	}
	
	
	
//	public static void main(String[] args) throws  Exception {
//		Class cls;
//		cls = Class.forName("zzia.zhantaocao.bean.User");//class_path Ϊ��·������������������java.lang.String
//		Object classObj = cls.newInstance();
////		Field fieldlist[] = cls.getDeclaredFields(); 
////	      for (int i = 0; i < fieldlist.length; i++) { 
////	       Field fld = fieldlist[i]; 
////	       System.out.println("name = " + fld.getName()); 
////	       System.out.println("decl class = " + fld.getDeclaringClass()); 
////	       System.out.println("type = " + fld.getType()); 
////	       int mod = fld.getModifiers(); 
////	       System.out.println("modifiers = " + Modifier.toString(mod)); 
////	       System.out.println("-----"); 
////	      } 
////	      
//		
//		 	Class[] parameterTypes = new Class[1];       
//		  	String fieldName = "wrTime" ;
//	        Field field = cls.getDeclaredField("wrTime");       
//	        parameterTypes[0] = field.getType();       
//	        StringBuffer sb = new StringBuffer();       
//	        sb.append("set");       
//	        sb.append(fieldName.substring(0, 1).toUpperCase());       
//	        sb.append(fieldName.substring(1));       
//	        Method method = cls.getMethod(sb.toString(), parameterTypes);  
//	        method.invoke(classObj, new Object[] { "asd" });     
//	        System.out.println(classObj.toString());
//	}
	
}
