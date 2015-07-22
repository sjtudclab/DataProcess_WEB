package cn.edu.sjtu.dcl.util;

import java.util.HashMap;

public class SearchUtil {
	
	public static final HashMap<Integer,String> jobMap=new HashMap<Integer,String>(){{
		put(1,"name");
		put(2,"modal");
		put(3,"developer");
	}};
	
	public static final HashMap<Integer,String> tempMap=new HashMap<Integer,String>(){{
		put(1,"name");
		put(2,"developer");
	}};
	
	public static final HashMap<Integer,String> historyMap=new HashMap<Integer,String>(){{
		put(1,"name");
		put(2,"user");
	}};

}
