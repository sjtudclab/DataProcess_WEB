package cn.edu.sjtu.dcl.mapreduce.generator;



public interface ICodeGenerator
{
	public void generate(String jcdl, String templatePath, String className, String namenode, String jobtracker);
}
