package cn.edu.sjtu.dcl.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHandler
{
	public static String getValue(String filePath, String key)
	{
		Properties props = new Properties();
		try
		{
			InputStream is = new BufferedInputStream(new FileInputStream(filePath));
			props.load(is);
			String value = props.getProperty(key);
			System.out.println(key + " = " + value);
			return value;
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args)
	{
		PropertiesHandler.getValue("conf.properties", "namenode");
	}
}
