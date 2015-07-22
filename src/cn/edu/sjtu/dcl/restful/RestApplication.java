package cn.edu.sjtu.dcl.restful;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class RestApplication extends Application {
	HashSet<Object> singletons = new HashSet<Object>();

	public RestApplication() {
		singletons.add(new RestJobService());
		singletons.add(new RestJobStatusService());
		singletons.add(new RestTemplateService());
		singletons.add(new RestUserService());
		singletons.add(new JsonTest());
	}

	@Override
	public Set<Class<?>> getClasses() {
		HashSet<Class<?>> set = new HashSet<Class<?>>();
		return set;
	}

	@Override
	public Set<Object> getSingletons() {
		return singletons;
	}
}
