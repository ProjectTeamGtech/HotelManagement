package com.apm.SSH;



import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.apm.common.web.common.helper.LoginInfo;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Basic
{
	HttpServletRequest request = ServletActionContext.getRequest();
//	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
	HttpSession session = request.getSession(true);
	LoginInfo loginInfo =  (LoginInfo)session.getAttribute("logininfo");
	public static void main(String[] args)
	{
		
	}
	public int sshExecute(){
		
		String hostname = loginInfo.getSshhostname();
		String username = loginInfo.getSshuser();
		String password = loginInfo.getSshpassword();

		try
		{
			/* Create a connection instance */

			Connection conn = new Connection(hostname,loginInfo.getPort());

			/* Now connect */

			conn.connect();
			/* Authenticate.
			 * If you get an IOException saying something like
			 * "Authentication method password not supported by the server at this stage."
			 * then please check the FAQ.
			 */

			
			boolean isAuthenticated = conn.authenticateWithPassword(username, password);

			if (isAuthenticated == false)
				throw new IOException("Authentication failed.");

			/* Create a session */

			Session sess = conn.openSession();
//			String path="/usr/local/tomcat7/webapps/YUVICAREDATA";
			String filePath = request.getRealPath("");
			
			if(filePath.contains("YUVICARE1")){
				filePath=filePath.replace("YUVICARE1", "YUVICAREDATA/DBbackup");
			}else if(filePath.contains("YUVICARETEST")){
				filePath=filePath.replace("YUVICARETEST", "YUVICAREDATA/DBbackup");
			}else if(filePath.contains("YUVICARE")){
			filePath=filePath.replace("YUVICARE", "YUVICAREDATA/DBbackup");
			}
			//String path=filePath;
			String path="/usr/local/tomcat7/webapps/YUVICAREDATA/DBbackup";
//			sess.execCommand("mkdir -p "+path+"");
			String mkdir="mkdir -p "+path+"";
			String sudo="sudo su";
			String chk="mysqldump -uroot -p"+loginInfo.getDbpassword()+" "+loginInfo.getClinicUserid()+" > "+path+"/"+loginInfo.getClinicUserid()+".sql";
			System.out.println(chk);
			//sess.execCommand(mkdir+" && "+ sudo + "&&"+loginInfo.getSshpassword()+" && "+chk);
			sess.execCommand(chk);

			System.out.println("Backup Done !");

			/* 
			 * This basic example does not handle stderr, which is sometimes dangerous
			 * (please read the FAQ).
			 */

			InputStream stdout = new StreamGobbler(sess.getStdout());

			BufferedReader br = new BufferedReader(new InputStreamReader(stdout));

			while (true)
			{
				String line = br.readLine();
				if (line == null)
					break;
				System.out.println(line);
			}

			/* Show exit status, if available (otherwise "null") */

			System.out.println("ExitCode: " + sess.getExitStatus());

			/* Close this session */

			sess.close();

			/* Close the connection */

			conn.close();

		}
		catch (IOException e)
		{
			e.printStackTrace(System.err);
			System.exit(2);
		}
		return 1;		
	}
}
