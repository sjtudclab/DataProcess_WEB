package cn.edu.sjtu.dcl.mapreduce.creator.impl;


import java.io.File;
import java.io.IOException;

import cn.edu.sjtu.dcl.mapreduce.creator.ICreator;
import cn.edu.sjtu.dcl.util.FileOperation;

public class MapReduceCreator implements ICreator
{
	private final String HADOOP_CORE_PATH = "hadoop-core-1.0.3.jar";
	
	public void create(String[] libPath, String jarName, String driverName)
	{
		try
		{
			File jartemp = new File(jarName + "temp");
			jartemp.mkdirs();
			compileJob(jartemp, libPath, jarName, driverName);
			File driverClass = new File(driverName + ".class");
			FileOperation.copyFile(driverClass, new File(jartemp.getCanonicalPath() + File.separator + driverName + ".class"));
			jarJob(jartemp, jarName, driverName);
			FileOperation.deleteFile(jartemp);
			
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void compileJob(File jartemp, String[] libPath, String jarName, String driverName) throws IOException
	{
		File libDirectory = new File(jartemp + File.separator + "lib");
		libDirectory.mkdirs();
		for(String li : libPath)
		{
			File src = new File(li);
			File des = new File(libDirectory + File.separator + src.getName());
			des.createNewFile();
			FileOperation.copyFile(src, des);
		}
		File driver = new File(driverName + ".java");
		ClassCompiler.compile(driver.getName(), libDirectory.listFiles());
		removeHadoopCore(libDirectory);
	}
	
	public void jarJob(File jartemp, String jarName, String driverName) throws IOException
	{
		JarHandler jh = new JarHandler();
		jh.setMAIN_CLASS(driverName);
		jh.jarDir(jartemp, new File(jarName + ".jar"));
	}
	
	private boolean removeHadoopCore(File dir)
	{
		File[] files = dir.listFiles();
		boolean result = false;
		for(int i =  0; i < files.length; i++)
		{
			if(files[i].getName().equalsIgnoreCase(HADOOP_CORE_PATH))
			{
				result = files[i].delete();
			}
		}
		return result;
	}
}
