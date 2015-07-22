package cn.edu.sjtu.dcl.mapreduce.executor.impl;

import java.io.IOException;

import cn.edu.sjtu.dcl.mapreduce.executor.IExecutor;

public class MapReduceExecutor implements IExecutor
{
	public String execute(String mapreducePath)
	{
		String cmd = "/opt/hadoop-1.0.3/bin/hadoop jar " + mapreducePath + ".jar";
		try
		{
			@SuppressWarnings("unused")
			Process proc = Runtime.getRuntime().exec(cmd);
			return "job is submitted";
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return "job submitted failed";
		}
	}
}
