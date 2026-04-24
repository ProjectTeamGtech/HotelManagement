package com.apm.Emr.eu.entity;



import javax.servlet.http.Part;

     public class FileName {
    	 
    	 private String fileName;
         
       public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

	//this method return name of file uploaded
      public static String getFileName(final Part part) {
       for (String content : part.getHeader("content-disposition").split(";")) {
          if (content.trim().startsWith("filename")) {
              return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
          
            }
        }
        return null;
    } 
}
