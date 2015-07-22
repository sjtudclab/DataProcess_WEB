package cn.edu.sjtu.dcl.oozie.creator;

import java.io.IOException;
import java.util.Vector;

public interface ICreator
{
	public void create(String hpdlPath, Vector<String> libs, String appPath) throws IOException;
}
