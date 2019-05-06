package top.ssxxlive;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
	
	static Properties prop = new Properties();
	
	static {
		try {
			prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	public static Object get(String key) {
		if(prop == null) {
			return null;
		}
		return prop.get(key);
		
	}
	
	public static void main(String[] args) {
		
	}
}
