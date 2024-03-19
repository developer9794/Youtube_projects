<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../tiles/inc.jsp"></jsp:include>
</head>

<body id="page-top" class="index">
	<jsp:include page="../tiles/menu.jsp"></jsp:include>
<%-- 	<jsp:include page="../tiles/header.jsp"></jsp:include> --%>

  

	<!-- Contact Section -->
	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Upload Documents</h2>

				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form name="myform" method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=uploadFile">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group" style="color: white;">
									Please Select a document
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="file" class="form-control" accept=".txt"
										 id="filename" name="filename"
										>   <!-- it will take an input as file from broswer button -->
									<p class="help-block text-danger"></p>
								</div>
							</div>
							<div class="col-md-6">

								
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-12 text-center">
								<div id="success"></div>
								<button type="submit" class="btn btn-xl">Upload Document</button><br><br>
							</div>
							<h2 align="center" style="color: white;"><%=request.getAttribute("MESSAGE")!=null?request.getAttribute("MESSAGE"):"" %></h2>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../tiles/footer.jsp"></jsp:include>
	<!-- Portfolio Modals -->
	<!-- Use the modals below to showcase details about your portfolio projects! -->




</body>

</html>
