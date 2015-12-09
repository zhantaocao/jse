package zzia.zhantaocao.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

public class ObjectMapping {
	
	public static String beanPackage ;
	static{
		Properties prop =  new  Properties();    
        InputStream in = Object. class .getResourceAsStream( "/zzia/zhantaocao/conf/database.properties" );
        try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    
        beanPackage = prop.getProperty("beanPackage") ;
	}
	
	public static Object getBeanInstance(String beanName) throws Exception{
		Class cls = Class.forName(beanPackage+beanName);
		Object classObj = cls.newInstance();
		return classObj ;
	}

	public static Method getGetMethod(Class objectClass, String fieldName) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		return objectClass.getMethod(sb.toString());
	}

	/**
	 * java反射bean的set方法
	 * 
	 * @param objectClass
	 * @param fieldName
	 * @return
	 */
	public static Method getSetMethod(Class objectClass, String fieldName) throws Exception {
			Class[] parameterTypes = new Class[1];
			Field field = objectClass.getDeclaredField(fieldName);
			parameterTypes[0] = field.getType();
			StringBuffer sb = new StringBuffer();
			sb.append("set");
			sb.append(fieldName.substring(0, 1).toUpperCase());
			sb.append(fieldName.substring(1));
			Method method = objectClass.getMethod(sb.toString(), parameterTypes);
			return method;
	}

	/**
	 * 执行set方法
	 * @param o执行对象
	 * @param fieldName属性
	 * @param value值
	 * @throws Exception 
	 */
	public static void invokeSet(Object o, String fieldName, Object value) throws Exception {
		Method method = getSetMethod(o.getClass(), fieldName);
		method.invoke(o, new Object[] { value });
	}

	/**
	 * 执行get方法
	 * @param o执行对象
	 * @param fieldName属性
	 * @throws Exception 
	 */
	public static Object invokeGet(Object o, String fieldName) throws Exception {
		Method method = getGetMethod(o.getClass(), fieldName);
		return method.invoke(o, new Object[0]);
	}

	public static void main(String[] args) throws Exception {
//		Class cls;
//		cls = Class.forName("zzia.zhantaocao.bean.User");// class_path
//		Object classObj = cls.newInstance();
//		invokeSet(classObj, "wrTime", "asd") ;
//		invokeSet(classObj, "wrDesc", "asd") ;
//		Method method = getSetMethod(cls, "wrTime") ;
//		System.out.println(classObj.toString());
	}
}
