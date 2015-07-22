package cn.edu.sjtu.dcl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
/***
 *
 *
 */
public class HDFSUtil
{
	public synchronized static FileSystem getFileSystem(String ip, int port)
	{
		FileSystem fs = null;
		String url = "hdfs://" + ip + ":" + String.valueOf(port);
		Configuration config = new Configuration();
		config.set("fs.default.name", url);
		try
		{
			fs = (DistributedFileSystem)FileSystem.get(config);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fs;
	}
	public synchronized static FileSystem getFileSystem(String namenode)
	{
		FileSystem fs = null;
		Configuration config = new Configuration();
		config.set("fs.default.name", namenode);
		try
		{
			fs = (DistributedFileSystem)FileSystem.get(config);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return fs;
	}
	public synchronized static void listNode(FileSystem fs)
	{
		DistributedFileSystem dfs = (DistributedFileSystem) fs;
		try
		{
			DatanodeInfo[] infos = dfs.getDataNodeStats();
			for (DatanodeInfo node : infos) {
				System.out.println("HostName: " + node.getHostName() + "/n"
						+ node.getDatanodeReport());
				System.out.println("--------------------------------");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 打印系统配置
	 * 
	 * @param fs
	 */
	public synchronized static void listConfig(FileSystem fs)
	{
		Iterator<Entry<String, String>> entrys = fs.getConf().iterator();
		while (entrys.hasNext())
		{
			Entry<String, String> item = entrys.next();
			System.out.println(item.getKey() + ": " + item.getValue());
		}
	}
	/**
	 * 创建目录和父目录
	 * 
	 * @param fs
	 * @param dirName
	 */
	public synchronized static void mkdirs(FileSystem fs, String dirName)
	{
		// Path home = fs.getHomeDirectory();
		Path workDir = fs.getWorkingDirectory();
		String dir = workDir + "/" + dirName;
		Path src = new Path(dir);
		// FsPermission p = FsPermission.getDefault();
		boolean succ;
		try {
			succ = fs.mkdirs(src);
			if(succ)
			{
				//log.info("create directory " + dir + " successed. ");
				System.out.println("create directory " + dir + " successed. ");
			}
			else
			{
				//log.info("create directory " + dir + " failed. ");
				System.out.println("create directory " + dir + " failed. ");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 删除目录和子目录
	 * 
	 * @param fs
	 * @param dirName
	 */
	public synchronized static void rmdirs(FileSystem fs, String dirName)
	{
		// Path home = fs.getHomeDirectory();
		Path workDir = fs.getWorkingDirectory();
		String dir = workDir + "/" + dirName;
		Path src = new Path(dir);
		boolean succ;
		try
		{
			succ = fs.delete(src, true);
			if(succ)
			{
				System.out.println("remove directory " + dir + " successed. ");
			}
			else
			{
				System.out.println("remove directory " + dir + " failed. ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 上传目录或文件
	 * 
	 * @param fs
	 * @param local
	 * @param remote
	 */
	public synchronized static void upload(FileSystem fs, String local,
			String remote) {
		// Path home = fs.getHomeDirectory();
		Path dst;
		if(!remote.startsWith("hdfs"))
		{
			Path workDir = fs.getWorkingDirectory();
			dst = new Path(workDir + "/" + remote);
		}
		else
			dst = new Path(remote);
		Path src = new Path(local);
		try
		{
			fs.copyFromLocalFile(false, true, src, dst);
			System.out.println("upload " + local + " to  " + remote + " successed. ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 下载目录或文件
	 * 
	 * @param fs
	 * @param local
	 * @param remote
	 */
	public synchronized static void download(FileSystem fs, String local,
			String remote) {
		// Path home = fs.getHomeDirectory();
		Path workDir = fs.getWorkingDirectory();
		Path dst = new Path(workDir + "/" + remote);
		Path src = new Path(local);
		try
		{
			fs.copyToLocalFile(false, dst, src);
			System.out.println("download from " + remote + " to  " + local
					+ " successed. ");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 字节数转换
	 * 
	 * @param size
	 * @return
	 */
	public synchronized static String convertSize(long size)
	{
		String result = String.valueOf(size);
		if (size < 1024 * 1024) {
			result = String.valueOf(size / 1024) + " KB";
		} else if (size >= 1024 * 1024 && size < 1024 * 1024 * 1024) {
			result = String.valueOf(size / 1024 / 1024) + " MB";
		} else if (size >= 1024 * 1024 * 1024) {
			result = String.valueOf(size / 1024 / 1024 / 1024) + " GB";
		} else {
			result = result + " B";
		}
		return result;
	}
	/**
	 * 遍历HDFS上的文件和目录
	 * 
	 * @param fs
	 * @param path
	 */
	public synchronized static void listFile(FileSystem fs, String path)
	{
		Path workDir = fs.getWorkingDirectory();
		Path dst;
		if (null == path || "".equals(path))
		{
			dst = new Path(workDir + "/" + path);
		}
		else
		{
			dst = new Path(path);
		}
		try
		{
			String relativePath = "";
			FileStatus[] fList = fs.listStatus(dst);
			for (FileStatus f : fList) {
				if (null != f) {
					relativePath = new StringBuffer()
							.append(f.getPath().getParent()).append("/")
							.append(f.getPath().getName()).toString();
					if (f.isDir()) {
						listFile(fs, relativePath);
					} else {
						System.out.println(convertSize(f.getLen()) + "/t/t"
								+ relativePath);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized static void write(FileSystem fs, String path,
			String data) {
		// Path home = fs.getHomeDirectory();
		Path workDir = fs.getWorkingDirectory();
		Path dst = new Path(workDir + "/" + path);
		try {
			FSDataOutputStream dos = fs.create(dst);
			dos.writeUTF(data);
			dos.close();
			System.out.println("write content to " + path + " successed. ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static void append(FileSystem fs, String path,
			String data) {
		// Path home = fs.getHomeDirectory();
		Path workDir = fs.getWorkingDirectory();
		Path dst = new Path(workDir + "/" + path);
		try {
			FSDataOutputStream dos = fs.append(dst);
			dos.writeUTF(data);
			dos.close();
			System.out.println("append content to " + path + " successed. ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized static String read(FileSystem fs, String path) throws IOException {
		String content = null;
		// Path home = fs.getHomeDirectory();
		Path workDir = fs.getWorkingDirectory();
		Path dst = new Path(workDir + "/" + path);
		//System.out.println(fs.exists(dst));
		try {
			// reading
			FSDataInputStream dis = fs.open(dst);
//			content = dis.readUTF();
//			dis.close();
//			System.out.println("read content from " + path + " successed. ");
			FileOutputStream fos = new FileOutputStream(new File("down.txt"));
			byte[] buffer = new byte[4096];
			int read;
			while((read =  dis.read(buffer)) != -1)
			{
				fos.write(buffer, 0, read);
				//fos.flush();
			}
			fos.flush();
			fos.close();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
}
