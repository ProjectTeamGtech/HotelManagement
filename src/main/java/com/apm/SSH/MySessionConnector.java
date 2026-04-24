package com.apm.SSH;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

class MySessionConnector extends Thread {
	String hostname;
	String username;
	InputStream in = null;
	OutputStream out = null;
	InputStream err = null;

	public static void main(String[] args) {
		new MySessionConnector();
	}

	public MySessionConnector()  {  
		this.hostname = "yuvicare.com";  
		this.username = "root";
		String password = "Youweka#$%2807"; 
		try   {    /* Create a connection instance */
    Connection conn = new Connection(hostname);    /* Now connect */   
    conn.connect(); 
    boolean isAuthenticated = conn.authenticateWithPassword(username, password);   
    if (isAuthenticated == false)    
    	throw new IOException("Authentication failed.");
    /* Create a session */
    Session sess = conn.openSession(); 
    sess.requestPTY("dumb", 90, 30, 0, 0, null);
    sess.startShell();  
    Scanner scan = new Scanner(System.in);  
    String myinput = null;    
    in = sess.getStdout();   
    out = sess.getStdin();   
    err = sess.getStderr();  
    String myCommand = null;    //
    new RemoteErrorConsumer().start(); 
    new RemoteConsumer().start();   
    char[] carr = null;     
    while(!(myCommand = scan.nextLine()).equalsIgnoreCase("Exit"))    {  
    	carr = myCommand.toCharArray() ;  
    	for(int i=0;i<carr.length;i++)     {   
    		out.write(carr[i]);     
    		}
    	out.write(13);  
    	}  
    }   catch(Exception e1)   {
    	e1.printStackTrace();  
    	} 
		}//Constructor Ends


  class RemoteConsumer extends Thread  { 
	  public void run()   {   
		  try{  
			  byte[] buff = new byte[8192]; 
			  int len = 0;   
			  int i=0;   
			  while (true)     { 
				  len = in.read(buff);  
				  for(i=0;i<len;i++){      {    
					  System.out.print((char)buff[i]);   
					  }      
				  }
			  }
    }    catch(Exception e1)    {    
    	e1.printStackTrace();    
    	} 
		  }//run ends    
	  }//class RemoteConsumer Ends   
  
  
  
  class RemoteErrorConsumer extends Thread  {        
	  public void run(){         
		  try{    
			  byte[] buff = new byte[8192];  
			  int len = 0;     
			  int i=0;    
			  while (true){  
				  len = err.read(buff);   
				  for(i=0;i<len;i++){
					  System.out.print((char)buff[i]);   
					  } 
				  }
     }     catch(Exception e2) { 
    	 e2.printStackTrace();    
     }  
	
}
  }
}