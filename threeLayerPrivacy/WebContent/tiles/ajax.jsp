<%@page import="com.helper.FileUploadDownloadHelper"%>
<%@page import="com.constant.ServerConstants"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="com.helper.HttpHelper"%>
<%@page import="java.util.List"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.IOException"%>
<%@page import="com.helper.UserModel"%>
<%@page import="java.io.ObjectOutputStream"%>
<%@page import="com.database.ConnectionManager"%>
<%@page import="com.helper.StringHelper"%>  
<%@page import="java.util.HashMap"%>     
<%@ page trimDirectiveWhitespaces="true" %>    
<% System.setProperty("java.awt.headless", "false");%>
<%
	String sMethod = StringHelper.n2s(request.getParameter("methodId"));
	String returnString = "";
	System.out.println("HIIIII");
	boolean bypasswrite=false;
	
	HashMap parameters = StringHelper.displayRequest(request);  
	
	if (sMethod.equalsIgnoreCase("registerNewUser")) { 
		returnString = ConnectionManager.insertUser(parameters);
	}
	
	else if (sMethod.equalsIgnoreCase("uploadFile")) 
	{ 
		HashMap uploadMap=HttpHelper.parseMultipartRequest(request);
		uploadMap.putAll(parameters);
		String key="";
		System.out.println("++++++++++Upload Map Is:"+uploadMap);
		if(session.getAttribute("USER_MODEL")==null)
		{
			 request.getRequestDispatcher("../pages/login.jsp").forward(request, response);
		}
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
		FileItem fi=(FileItem)uploadMap.get("filenameITEM");
		System.out.println("File is: "+fi);
		String fnemaee =fi.getName();
		System.out.println("FileName is: "+fnemaee);
		String[] foext = fnemaee.toString().split("\\.");
		System.out.println("ar lengthh: "+foext.length);
		for(int i =0; i<foext.length; i++){
			System.out.println("arr: " + foext[i]);
		}
		String fileExtension = foext[foext.length-1];
		System.out.println("File extension is: "+ fileExtension);
		if(fileExtension.trim().equalsIgnoreCase("txt")){
			
		
		key = ConnectionManager.decriptionusingRSA(um.getUserkey(), um.getEmailid().trim());
		try
		{
		System.out.println(new File(ServerConstants.LOCAL_UPLOAD).getCanonicalPath());   
		} catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		  
		/* File f = new File(ServerConstants.LOCAL_UPLOAD + "/" + fi.getName());
		fi.write(f); */
		String message=ConnectionManager.uploadDocument(fi,um.getUid(),um.getRole(),um.getName(),key);  
		// if file uploaded sucessfully it will return the file upload sucessfully! File indexing completed
		  
		
		request.setAttribute("MESSAGE", message);  /* set session ("Message") for retrive the list of file */
		request.getRequestDispatcher("../pages/fileUpload.jsp").forward(request, response);
		}else{
			returnString = "<h1>Please upload only txt files!</h1>";
		}
	}

	else if (sMethod.equalsIgnoreCase("deleteFile")) { 
	
		if(session.getAttribute("USER_MODEL")==null){
			 request.getRequestDispatcher("../pages/login.jsp").forward(request, response);   /* forword request to login.jsp for login perpose */
		}
		      
		UserModel um=(UserModel)session.getAttribute("USER_MODEL");
 		parameters.put("userId", um.getUid());
		String ret = ConnectionManager.deleteDocument(parameters);  
		response.sendRedirect(request.getContextPath()+"/pages/fileList.jsp");
	}
	else if (sMethod.equalsIgnoreCase("downloadFile")) { 
		String fid = StringHelper.n2s(request.getParameter("fileId"));
		String key = StringHelper.n2s(request.getParameter("key"));
		
		System.out.println("downloadFile>>>>>>fid"+fid);
		System.out.println("downloadFile>>>>>>key"+key);
		String path = ConnectionManager.getFilePath(fid,key); 
		FileUploadDownloadHelper.downloadFile(path, response);
		/* String resp = ConnectionManager.downloadFile(parameters); 
		request.setAttribute("MESSAGE", resp);
		request.getRequestDispatcher("../pages/search.jsp").forward(request, response); */
	
	}                           
	else if (sMethod.equalsIgnoreCase("checkLogin")) {
		UserModel um= ConnectionManager.checkLogin(parameters);
		if(um!=null){
	session.setAttribute("USER_MODEL", um);
	returnString="1";
		}else{
	returnString="2";
		}    
	}
	
	else if (sMethod.equalsIgnoreCase("logout")) {
	session.removeAttribute("USER_MODEL");
	bypasswrite=true;
%>
			<script>
			window.location.href='<%=request.getContextPath()%>/pages/login.jsp';
			</script>   
			<%
	}
	if(!bypasswrite){
	out.println(returnString);
	}
%>
