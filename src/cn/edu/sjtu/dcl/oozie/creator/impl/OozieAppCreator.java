package cn.edu.sjtu.dcl.oozie.creator.impl;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import cn.edu.sjtu.dcl.oozie.creator.ICreator;
import cn.edu.sjtu.dcl.util.FileOperation;

public class OozieAppCreator implements ICreator
{

	public void create(String hpdlPath, Vector<String> libs, String appPath) throws IOException
	{
		File app = new File(appPath);
		app.mkdirs();
		File src = new File(hpdlPath);
		File des = new File(app + File.separator + "workflow.xml");
		des.createNewFile();
		FileOperation.copyFile(src, des);
		File libDirectory = new File(app + File.separator + "lib");
		libDirectory.mkdirs();
		for(int i = 0; i < libs.size(); i++)
		{
			src = new File(libs.get(i));
			des = new File(libDirectory + File.separator + src.getName());
			if(!des.exists())
			{
				des.createNewFile();
				FileOperation.copyFile(src, des);
			}
		}
		FileOperation.deleteFile(new File(hpdlPath));
	}

}
