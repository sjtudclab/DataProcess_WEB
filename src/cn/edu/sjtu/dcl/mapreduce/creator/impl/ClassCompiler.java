package cn.edu.sjtu.dcl.mapreduce.creator.impl;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class ClassCompiler
{
	public static boolean compile(String file, File libs[]) throws IOException
	{
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
	    //DiagnosticCollector<JavaFileObject> collector = new DiagnosticCollector<JavaFileObject>();
	    //StandardJavaFileManager fileManager = compiler.getStandardFileManager(collector, null, null);
	    StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
	    File[] files1 = new File[]{new File(file)};
	    Iterable<? extends JavaFileObject> compilationUnits1 =
	            fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files1));
	//    List<String> options = new ArrayList<String>();
	//    options.addAll(Arrays.asList("-classpath", "hadoop-0.20.2-core.jar"));
	    String classpath = "";
	    for(File c : libs)
	    {
	    	classpath += c.getCanonicalPath();
	    	classpath += File.pathSeparator;
	    }
	    classpath = classpath.substring(0, classpath.length() - 1);
	    System.out.println(classpath);
	    Iterable<String> options = Arrays.asList("-classpath", classpath); 
	    //CompilationTask task = compiler.getTask(null, fileManager, collector, options, null, compilationUnits1);
	    CompilationTask task = compiler.getTask(null, fileManager, null, options, null, compilationUnits1);
	    boolean result = task.call();
	    fileManager.close();
	    //List<Diagnostic<? extends JavaFileObject>> diagnostics = collector.getDiagnostics(); 
	    return result;
	}
}
