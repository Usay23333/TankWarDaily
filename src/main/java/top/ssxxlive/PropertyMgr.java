package top.ssxxlive;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

	private static final Properties PROP = new Properties();

	private PropertyMgr() {
	}

	static {
		try {
			PROP.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public static Properties getInstance() {
		return PROP;
	}
}
