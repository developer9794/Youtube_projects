<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../tiles/inc.jsp"></jsp:include>
</head>

<body id="page-top" class="index">
	<jsp:include page="../tiles/menu.jsp"></jsp:include>



	<!-- Contact Section -->
	<section id="contact">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<h2 class="section-heading">Login</h2>

				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form name="myform" id="myform" action="javascript:fnSubmit();">
						<div class="row">
							<div class="col-md-12" style="padding-left: 400px; padding-right: 400px;">
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="User Name *" id="uname" name="uname"
										pattern="^[A-Za-z]{1,20}$"
										title="Please enter characters only" required
										data-validation-required-message="Please enter user name.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="password" class="form-control"
										placeholder="Password *" id="password" name="password"
										 required data-validation-required-message="Please enter last name.">
									<p class="help-block text-danger"></p>
								</div>
							</div>
							
							<div class="clearfix"></div>
							<div class="col-lg-12 text-center">
								<div id="success"></div>
								<button type="submit" class="btn btn-xl">Login</button>
							</div>
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
<script>

function fnSubmit(){	

	
	
	 var str = $("#myform" ).serialize();
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=checkLogin",  
			str,
			function(data) {
data=$.trim(data);

if (data==1) {
	window.location.href="<%=request.getContextPath()%>/pages/home.jsp";
} else {
alert("Please Enter Valid credentials");
}

$('#myform')[0].reset();

			});


}

  
</script>
</html>
