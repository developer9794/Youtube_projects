<!DOCTYPE html>
<%@page import="com.helper.DocumentModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.database.ConnectionManager"%>
<html lang="en">

<head>
<jsp:include page="../tiles/inc.jsp"></jsp:include>
</head>
  
<body id="page-top" class="index">
	<%@include file="../tiles/menu.jsp" %>
<%-- 	<jsp:include page="../tiles/header.jsp"></jsp:include> --%>

<%
List documents = ConnectionManager.getDocumentList(um.getUid());
%>

	<!-- Contact Section -->
	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">List of Documents</h2>

				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form name="myform" method="post" enctype="multipart/form-data" action="">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" style="color: white;margin: auto;">
								<BR><BR>  
								<table style="margin: auto;padding: 5px;width: 60%; ">
								<tr>      
									<td>Sr.No</td>
									<td>File Name</td>
									<td>File Size</td>
									<td>Delete</td> 
									
								</tr>
								<%
								int i=1;
								for (Iterator iterator = documents.iterator(); iterator.hasNext();) {
									DocumentModel object = (DocumentModel) iterator.next();
									%>   
										<tr>
											<td><%=i %>&nbsp;&nbsp;</td>
											<td><a href="javascript:fnView(<%=object.getDocId()%>)" ><%=object.getDocName() %>&nbsp;&nbsp;</a></td>
											<td><%=object.getDocSize() %> bytes&nbsp;&nbsp;</td>
											<td><a href="#" onclick="if(confirm('Are You Sure?')){window.location.href='<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=deleteFile&fileId=<%=object.getDocId()%>';}">Delete</a></td>
										</tr>
									<% i++;
								}
								%>
								</table>
								
									<p class="help-block text-danger"></p>
								</div>
								
								

							</div>
							<div class="col-md-6">

							</div>
							<div class="clearfix"></div>
							
							<h2><%=request.getAttribute("MESSAGE")!=null?request.getAttribute("MESSAGE"):"" %></h2>
						</div>
					</form>
				</div>
			</div>
			<div class="row" id="hiddendiv" hidden="true">
				<div class="col-md-12">
				<textarea contenteditable="false" disabled id="resultText" readonly draggable="false" 
				style="width: 1130px; height: 660px"></textarea>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../tiles/footer.jsp"></jsp:include>
	<!-- Portfolio Modals -->
	<!-- Use the modals below to showcase details about your portfolio projects! -->



<script type="text/javascript">

function fnView(fileId){
	var key = prompt("Please enter Uploader's Key to Decrypt", "");
	<%
	UserModel um1 = (UserModel)session.getAttribute("USER_MODEL");
	
	%>
	if(key!=<%=ConnectionManager.decriptionusingRSA(um1.getUserkey(), um.getEmailid().trim())%>)
	{
		alert("Invalid Key");
	}
	else{
	$.ajax({
		  type: "POST",
		  url: "<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=downloadFile&fileId="+fileId+"&key=" + key+"",
		  dataType: "text"
		  }).done(function(msg){
			  
			  $("#hiddendiv").show();
			  $('#resultText').val(msg);
			  
		  });
	}
}

</script>

</body>
</html>
