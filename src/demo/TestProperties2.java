package demo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.junit.Test;
/*
 * 配置文件，用于存放一些系统的配置信息，如：调用的webservice地址、
 * 公共变量、数据库连接串等，便于系统升级维护
 */
public class TestProperties2 {
	private String param1;
	
//	获取JVM的系统属性
	@Test
	public void test1() {
		Properties pps = System.getProperties();
		pps.list(System.out);
	}
//	读取test.properties文件中的内容
	@Test
	public void test2() throws FileNotFoundException, IOException {
		Properties pps = new Properties();
			pps.load(new FileInputStream("D://test.properties"));
			Enumeration enum1 = pps.propertyNames();//得到配置文件的名字
			while(enum1.hasMoreElements()) {
			String strKey = (String) enum1.nextElement();
			String strValue = pps.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}
	}
//	一个比较综合的实例根据key读取value读取properties的全部信息写入新的properties信息
	
	 //根据Key读取Value
    public static String GetValueByKey(String filePath, String key) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream (new FileInputStream(filePath));  
            pps.load(in);
            String value = pps.getProperty(key);
            System.out.println(key + " = " + value);
            return value;
            
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    //读取Properties的全部信息
    public static void GetAllProperties(String filePath) throws IOException {
        Properties pps = new Properties();
        InputStream in = new BufferedInputStream(new FileInputStream(filePath));
        pps.load(in);
        Enumeration en = pps.propertyNames(); //得到配置文件的名字
        
        while(en.hasMoreElements()) {
            String strKey = (String) en.nextElement();
            String strValue = pps.getProperty(strKey);
            System.out.println(strKey + "=" + strValue);
        }
        
    }
    
    //写入Properties信息
    public static void WriteProperties (String filePath, String pKey, String pValue) throws IOException {
        Properties pps = new Properties();
        
        InputStream in = new FileInputStream(filePath);
        //从输入流中读取属性列表（键和元素对） 
        pps.load(in);
        //调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。  
        //强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
        OutputStream out = new FileOutputStream(filePath);
        pps.setProperty(pKey, pValue);
        //以适合使用 load 方法加载到 Properties 表中的格式，  
        //将此 Properties 表中的属性列表（键和元素对）写入输出流  
        pps.store(out, "Update " + pKey + " name");
    }
    
    public static void main(String [] args) throws IOException{
//        String value = GetValueByKey("D://test.properties", "initYears1");
//        System.out.println(value);
        GetAllProperties("D://Test.properties");
//        WriteProperties("D://Test.properties","long", "212");
    }
    
	
}
