package cn.edu.sjtu.dcl.util;

import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import net.neoremind.sshxcute.core.ConnBean;
import net.neoremind.sshxcute.core.Result;
import net.neoremind.sshxcute.core.SSHExec;
import net.neoremind.sshxcute.exception.TaskExecFailException;
import net.neoremind.sshxcute.task.CustomTask;
import net.neoremind.sshxcute.task.impl.ExecCommand;

public class SshUtil {

	private SshUtil() {

	}

	public static Result RunCmd(String ipAddr, String userName,
			String password, String command) {
		// Initialize a SSHExec instance without referring any object.
		SSHExec ssh = null;
		Result res = null;
		// Wrap the whole execution jobs into try-catch block
		try {
			// Initialize a ConnBean object, parameter list is ip, username,
			// password
			ConnBean cb = new ConnBean(ipAddr, userName, password);
			// Put the ConnBean instance as parameter for SSHExec static method
			// getInstance(ConnBean) to retrieve a real SSHExec instance
			ssh = SSHExec.getInstance(cb);
			// Create a ExecCommand, the reference class must be CustomTask
			CustomTask ct = new ExecCommand(command);
			// Create a ExecShellScript, the reference class must be CustomTask
			// Connect to server
			ssh.connect();
			// Upload sshxcute_test.sh to /home/tsadmin on remote system
			// ssh.uploadSingleDataToServer("data/sshxcute_test.sh",
			// "/home/tsadmin");
			// Execute task
			// Execute task and get the returned Result object
			res = ssh.exec(ct);
			
			// Check result and print out messages.

		} catch (TaskExecFailException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			ssh.disconnect();
			return res;
		}
	}

	public static void RunCmd2(String ipAddr, String userName, String password,
			String command) throws Exception {
		try{
		JSch jsch = new JSch();

		String host = null;

		Session session = jsch.getSession(userName, ipAddr, 22);

		/*
		 * String xhost="127.0.0.1"; int xport=0; String
		 * display=JOptionPane.showInputDialog("Enter display name",
		 * xhost+":"+xport); xhost=display.substring(0, display.indexOf(':'));
		 * xport=Integer.parseInt(display.substring(display.indexOf(':')+1));
		 * session.setX11Host(xhost); session.setX11Port(xport+6000);
		 */

		// username and password will be given via UserInfo interface.
		//UserInfo ui = new MyUserInfo(password);

		//session.setUserInfo(ui);
		
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.setPassword(password);
		session.connect();

		Channel channel = session.openChannel("exec");
		((ChannelExec) channel).setCommand(command);
		
		// X Forwarding
		// channel.setXForwarding(true);

		// channel.setInputStream(System.in);
		channel.setInputStream(null);

		// channel.setOutputStream(System.out);

		// FileOutputStream fos=new FileOutputStream("/tmp/stderr");
		// ((ChannelExec)channel).setErrStream(fos);
		((ChannelExec) channel).setErrStream(System.err);

		InputStream in = channel.getInputStream();
		OutputStream out = channel.getOutputStream();
		
		channel.connect();

		byte[] tmp = new byte[1024];
		while (true) {
			while (in.available() > 0) {
				int i = in.read(tmp, 0, 1024);
				if (i < 0)
					break;
				System.out.print(new String(tmp, 0, i));
				StringBuilder sb = new StringBuilder();
				
			}
			if (channel.isClosed()) {
				System.out.println("exit-status: " + channel.getExitStatus());
				throw new Exception(String.format("command exit with exitcode:", channel.getExitStatus()));
			}
		}
		
		//channel.disconnect();
		//session.disconnect();
		}catch(Exception e)
		{
			throw e;
		}
	}
}
