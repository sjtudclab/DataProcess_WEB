package cn.edu.sjtu.dcl.oozie.executor.impl;

import java.util.Properties;

import org.apache.hadoop.fs.FileSystem;
import org.apache.oozie.client.OozieClient;
import org.apache.oozie.client.OozieClientException;

import cn.edu.sjtu.dcl.oozie.executor.IExecutor;
import cn.edu.sjtu.dcl.util.HDFSUtil;
import cn.edu.sjtu.dcl.util.PropertiesHandler;

public class OozieExecutor implements IExecutor {
	private String namenode;
	private String basepath;
	private String oozieconf = "/WEB-INF/classes/oozie.conf";

	public static String OOZIE_URL = "";

	public OozieExecutor(String namenode, String basepath) {
		this.namenode = namenode;
		this.basepath = basepath;
	}

	public void setNamenode(String namenode) {
		this.namenode = namenode;
	}

	public String execute(String appPath) {
		String clusterAppPath = namenode
				+ "/app/"
				+ appPath.substring(appPath.indexOf("hpdl/") + 5,
						appPath.length());
		
		if (OOZIE_URL.isEmpty()) {
			OOZIE_URL = PropertiesHandler.getValue(basepath + oozieconf, "url");
		}

		OozieClient wc = new OozieClient(OOZIE_URL);
		Properties p = wc.createConfiguration();
		p.setProperty(OozieClient.APP_PATH, clusterAppPath);
		try {
			String jobId = wc.run(p);

			System.out.println(jobId);

			return jobId;
			// return jobId + " is submitted";
		} catch (OozieClientException e) {
			e.printStackTrace();
			return null;
			// return "oozie execution failed";
		}
	}

	public void employ(String appPath) {
		FileSystem fs = HDFSUtil.getFileSystem(namenode);
		String clusterAppPath = namenode
				+ "/app/"
				+ appPath.substring(appPath.indexOf("hpdl/") + 5,
						appPath.length());
		HDFSUtil.upload(fs, appPath + "/app", clusterAppPath);
	}
}
