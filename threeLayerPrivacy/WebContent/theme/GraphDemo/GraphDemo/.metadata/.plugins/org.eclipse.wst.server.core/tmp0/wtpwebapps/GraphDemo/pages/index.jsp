<%@page import="com.database.ConnectionManager" %>
<%@ page import="com.beans.UserModel" %>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../tiles/header_files.jsp"></jsp:include>
</head>
<%
	String name="User";
	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
	String userid = um.getUserid();
	if (um != null){
%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:include page="../tiles/header.jsp"></jsp:include>
  	<jsp:include page="../tiles/left_sidebar.jsp"></jsp:include>
 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Dashboard </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Small boxes (Stat box) -->
      <div class="row">
<%
	HashMap hm = null;
	List list = ConnectionManager.getvehiclefuelDetails(userid); 
	if(list.size()>0){
		for(int i=0;i<list.size();i++){
			hm = (HashMap) list.get(i);
%>    
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-aqua">
            <div class="inner">
              <h3><%=hm.get("fuelqty")+" ltr" %></h3>
              <p><%=hm.get("vehicletype")+"("+hm.get("vehicleno")+")" %></p>
            </div>
          </div>
        </div>
        <!-- ./col -->
        <div class="col-lg-3 col-xs-6">
          <!-- small box -->
          <div class="small-box bg-yellow">
            <div class="inner">
              <h3><%=hm.get("kmdriven")+" km" %></h3>
              <p><%=hm.get("vehicletype")+"("+hm.get("vehicleno")+")" %></p>
            </div>
          </div>
        </div>
<%
		}
	}
%>      
      </div>
      <!-- /.row -->
      <!-- Main row -->
      <div class="row">
        
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
		<jsp:include page="../tiles/footer.jsp"></jsp:include>
  
</div>
<!-- ./wrapper -->

	<jsp:include page="../tiles/footer_files.jsp"></jsp:include>
</body>
<%
	}else{
%>
		<jsp:include page="../pages/login.jsp"></jsp:include>
<%		
	}
%>
</html>
