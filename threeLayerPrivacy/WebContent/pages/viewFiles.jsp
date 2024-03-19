<!DOCTYPE html>
<%@page import="com.helper.UserModel"%>
<%@page import="com.helper.FollowerModel"%>
<%@page import="com.helper.DocumentModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.database.ConnectionManager"%>
<html lang="en">

<head>
<jsp:include page="../tiles/inc.jsp"></jsp:include>
</head>
  
<body id="page-top" class="index">
	<%@include file="../tiles/menu.jsp"%>
	<%-- 	<jsp:include page="../tiles/header.jsp"></jsp:include> --%>

	<%
		String uid = um.getUid();
		List files = ConnectionManager.getFileList(uid);
	%>
	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">File Status</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-12">
					<form action=""
						method="post" id="test">
						<div class="form-group" style="color: white; margin: auto;">
							<BR> <BR>
							<table
								style="margin: auto; padding: 5px; width: 50%; color: white;">
								<tr>
									<th>Sr.No</th>
									<th>File Name</th>
									<th><center>File Action</center></th>
									<!-- <th>Action</th> -->
									<!-- <th>Delete</td> -->
								</tr>

								<%
									for (int i = 0; i < files.size(); i++) {
										DocumentModel obj = (DocumentModel) files.get(i);
								%>
								<tr>
									<td><%=i + 1%></td>
									<td><%=obj.getDocName()%></td>

									
									
									<td><center>
											<a href="#" onclick="fnDownload('<%=obj.getDocId()%>')">Download</a>
										</center></td>
																	
									
								</tr>
								<%
									}
								%>
							</table>
							<p class="help-block text-danger"></p>
						</div>
					</form>
				</div>
				<div class="col-md-6"></div>
				<div class="clearfix"></div>
			</div>
			<BR> <BR> <BR> <BR> <BR>
			<div class="row" id="hiddendiv" hidden="true">
				<div class="col-md-12">
					<textarea contenteditable="false" disabled id="resultText" readonly
						draggable="false" style="width: 1130px; height: 660px"></textarea>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../tiles/footer.jsp"></jsp:include>
	<!-- Portfolio Modals -->
	<!-- Use the modals below to showcase details about your portfolio projects! -->
</body>
<script type="text/javascript">

function fnDownload(fileId)
{ 
	<%
	UserModel um1 = (UserModel)session.getAttribute("USER_MODEL");
	
	%>
	var key = prompt("Please enter Uploader's Key to Decrypt", "");
	if(key!=<%=ConnectionManager.decriptionusingRSA(um1.getUserkey(),um1.getEmailid().trim())%>)
		{
			alert("Invalid Key");
		}   
	else{
			var url = $("#test").attr("action");
	  		//var newParam = "&new=123";
	  		url = "<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=downloadFile&fileId=" + fileId+"&key=" + key+"";
	  		$("#test").attr("action", url);
	 		var url2 = $("#test").attr("action");
	  		//alert(url2);
	  		$("#test").submit();
		}
}





</script>
</html>
