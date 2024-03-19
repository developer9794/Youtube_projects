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
					<h2 class="section-heading">User Registration</h2>

				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<form name="myform" id="myform" action="javascript:fnSubmit();">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="First Name *" id="fname" name="fname"
										pattern="^[A-Za-z]{1,20}$"
										title="Please enter characters only" required
										data-validation-required-message="Please enter first name.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="Last Name *" id="lname" name="lname"
										pattern="^[A-Za-z]{1,20}$"
										title="Please enter characters only" required
										data-validation-required-message="Please enter last name.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="email"  pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$"
                                       title="Please enter valid Email id"
                                        class="form-control" required="required"
										placeholder="Your Email *" id="email" name="email"
										onchange="checkEmail()" 
										data-validation-required-message="Please enter your email address.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="tel" class="form-control"
										placeholder="Your Phone *" id="phone" name="phone"
										pattern="[0-9]{10}" title="Please enter correct phone"  maxlength="10"
										required
										>
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="user Name *" id="uname" name="uname"
										
										title="Please enter characters only" required
										data-validation-required-message="Please enter user name.">
									<p class="help-block text-danger"></p>
								</div>

							</div>
							<div class="col-md-6">

								<div class="form-group">
									<input type="password" 
										placeholder="Password *" id="password" name="password"
										"form-control" required="required"
	                                    pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
                                       title="Password should contain Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
                                       class="form-control" required="required"
										data-validation-required-message="Please enter password name.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="password" 
										placeholder="Confirm Password *" id="cpass" name="cpass"
										"form-control" required="required"
                                        pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"
                                      title="Password should contain Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character"
                                      class="form-control" required="required"
	                                   data-validation-required-message="Please enter confirm password name.">
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<input type="password" class="form-control"
										placeholder="Enter Encryption Key *" id="encKey" name="encKey" maxlength="4">
										<!-- onchange="isPrime();"> -->
										
									<p class="help-block text-danger"></p>
								</div>
								<div class="form-group">
									<textarea class="form-control" placeholder="Your Address *"
										style="height: 70px;" id="address" name="address" required
										data-validation-required-message="Please enter address."></textarea>
									<p class="help-block text-danger"></p>
								</div>
							</div>
							<div class="clearfix"></div>
							<div class="col-lg-12 text-center">
								<div id="success"></div>
								<button type="submit" class="btn btn-xl">Register</button>
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

	if($('#password').val()!=$('#cpass').val()){
		alert('Password and confirm password do not match!');
		return;
	}
	
	 var str = $("#myform" ).serialize();
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=registerNewUser",
			str,
			function(data) {
data=$.trim(data);  
alert(data);
$('#myform')[0].reset();

			});


}
function checkEmail() {

    var email = document.getElementById('email');
    var filter = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

    if (!filter.test(email.value)) {
    alert('Please provide a valid email address');
    email.focus;
    return false;
 }
}
function isPrime(){
	
	var flag=true;

	var number=$('#pk').val();
	//alert(number);
	
	 if (isNaN(number)) 
	  {
	    alert("Must input numbers");
	   return false;
	  }
	for(var i=2; i<=number/2; i++){
        if(number % i == 0){
           flag=false;
        }
    }
    if(flag==false){
    	alert("Public key should  prime Number");
    	
    }
    
}
</script>
</html>
