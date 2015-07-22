package cn.edu.sjtu.dcl.util;

import java.util.HashMap;
import java.util.Map;

public class ProjectConstants {

	public static final String USER_SESSION_KEY = "sessionUser";
	public static final Map<Integer,String> jobSearchMap=new HashMap<Integer,String>(){{
		put(1,"developer");
		put(2,"model");
		put(3,"name");
	}};
}
