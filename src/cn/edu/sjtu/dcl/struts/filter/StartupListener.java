package cn.edu.sjtu.dcl.struts.filter;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartupListener extends ContextLoaderListener implements
		ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {

		super.contextInitialized(event);

		ServletContext context = event.getServletContext();
		setServletContext(context);

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		setAppContext(ctx);

		setupContext(context);
	}

	public static void setupContext(ServletContext context) {

	}

	public static Object getBean(String beanName) {
		return appContext.getBean(beanName);
	}

	/** 
*/
	private static ApplicationContext appContext;

	public static ApplicationContext getAppContext() {
		return appContext;
	}

	public static void setAppContext(ApplicationContext ctx) {
		appContext = ctx;
	}

	/**
	 * ServletContext
	 */
	private static ServletContext servletContext;

	/**
	 * @return Returns the servletContext.
	 */
	public static ServletContext getServletContext() {
		return servletContext;
	}

	/**
	 * @param servletContext
	 *            The servletContext to set.
	 */
	public static void setServletContext(ServletContext servletContext) {
		StartupListener.servletContext = servletContext;
	}

	public static String getServletWebInfRealPath() {
		return servletContext.getRealPath("WEB-INF");
	}

}
