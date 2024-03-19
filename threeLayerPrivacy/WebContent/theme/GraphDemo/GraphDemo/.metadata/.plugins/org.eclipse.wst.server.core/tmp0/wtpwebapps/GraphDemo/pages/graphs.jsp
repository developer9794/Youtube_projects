<%@page import="com.helper.StringHelper"%>
<%@page import="com.beans.*"%>
<%@page import="com.database.ConnectionManager"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="../tiles/header_files.jsp"></jsp:include>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
	<jsp:include page="../tiles/header.jsp"></jsp:include>
  	<jsp:include page="../tiles/left_sidebar.jsp"></jsp:include>

<%
	HashMap hm = null;
	String lablelist1 = null;
	String lablelist2 = null;
	String data1 = null;
	String data2 = null;
// 	lablelist1 = lablelist2 = "[";
// 	data1= data2 = ("[");
	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
	String userid=um.getUserid();

%> 
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>Fuel Graphs </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <section class="col-lg-4 connectedSortable">
			<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">Vehicle</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->
					<form role="form" name="myform" id="myform" action="javascript:fnShow();">
						<div class="box-body">
							<div class="form-group">
								<label>Select Vehicle</label>
								<select class="form-control" name="vehicletype" id="vehicletype">
									<option>Select Type</option>
<%
	VehicleDetails vd = null;
	List list = ConnectionManager.getvehicleDetails(userid);
	if(list.size()>0){
		for(int i=0;i<list.size();i++){
			vd = (VehicleDetails) list.get(i);
%>								 
									<option value="<%=vd.getVehicleid()%>"><%=vd.getVehicletype() + " (" + vd.getVehicleno()+")"%></option>
<%
		}
	}
%>		
								</select>
							</div>
							<div class="form-group">
								<label>Select Graph</label>
								<select class="form-control" name="graphtype" id="graphtype">
									<option>Select Type</option>								 
									<option value="Petrol Filled">Petrol Filled</option>				 
									<option value="KM Driven">KM Driven</option>
								</select>
							</div>
						</div>
						<!-- /.box-body -->
						<div class="box-footer">
							<button type="submit" class="btn btn-primary">Show Graph</button>
						</div>
					</form>
					<!-- form end -->
				</div>
				<!-- /.box -->  		
         
        </section>
        <!-- /.Left col -->
        
        <!-- right col (We are only adding the ID to make the widgets sortable)-->
        <section class="col-lg-8 connectedSortable">
			<div class="box box-solid bg-light-blue-gradient" id="graphbox" style="display:none;">
            <div class="box-header ui-sortable-handle" style="cursor: move;">
              <i class="fa fa-th"></i>

              <h3 class="box-title"><label  id="graphname"></label></h3>

              <div class="box-tools pull-right">
                <button type="button" class="btn btn-primary btn-sm pull-right" data-widget="collapse"><i class="fa fa-minus"></i>
                </button>
                <button type="button" class="btn btn-primary btn-sm pull-right" data-widget="remove"><i class="fa fa-times"></i>
                </button>
              </div>
            </div>
            <div class="box-body border-radius-none">
              <div class="chart" id="line-chart" style="height: 500px;">
              	<canvas id="canvas1" height="450" width="600"></canvas>
              	<canvas id="canvas2" height="450" width="600"></canvas>
              </div>
            </div>
            <!-- /.box-body -->
            <!-- /.box-footer -->
          </div>
        </section>
        <!-- right col -->
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
<script>
function fnShow(){
	var str = $("#myform" ).serialize();
//   	alert(str);
  	
	var graphT = document.getElementById("graphtype");
	var graph = graphT.options[graphT.selectedIndex].value;	
	var vehicleT = document.getElementById("vehicletype");
	//var vid = vehicleT.options[vehicleT.selectedIndex].value;
// 	alert(graphT,vid);
	alert("vehicleT"+vehicleT.value);

	document.getElementById("graphname").innerHTML = graph+ " Graph";
	document.getElementById("graphbox").style.display = "block";
	
	$.post("<%=request.getContextPath()%>/tiles/ajax.jsp?methodId=graph",
			str, function(data) {
				data = $.trim(data);
				alert(data);
				labels=data.split("_")[0];
				data=data.split("_")[1];
				
				if(graph == "Petrol Filled"){
					document.getElementById("canvas1").style.display = "block";
					document.getElementById("canvas2").style.display = "none";
					graph1(labels.split(','),data.split(','));
				}else if(graph == "KM Driven"){	
					document.getElementById("canvas1").style.display = "none";
					document.getElementById("canvas2").style.display = "block";
					graph2(labels.split(','),data.split(','));
				}
			});
	
	
}

function graph1(labelsData,axisData){
	var lineChartData = {
		labels :labelsData,
		datasets : [
			{
				label: "My First dataset",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)", 
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "rgba(0,0,0,0)",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data :axisData
			}
		]
	}
	
	var ctx = document.getElementById("canvas1").getContext("2d");
	window.myLine = new Chart(ctx).Line(lineChartData, {
		responsive: true
	});
}

function graph2(labelsData,axisData){
	var lineChartData = {
		labels :labelsData,
		datasets : [
			{
				label: "My First dataset",
				fillColor : "rgba(220,220,220,0.2)",
				strokeColor : "rgba(220,220,220,1)", 
				pointColor : "rgba(220,220,220,1)",
				pointStrokeColor : "#fff",
				pointHighlightFill : "rgba(0,0,0,0)",
				pointHighlightStroke : "rgba(220,220,220,1)",
				data :axisData
			}
		]
	}
	
	var ctx = document.getElementById("canvas2").getContext("2d");
	window.myLine = new Chart(ctx).Line(lineChartData, {
		responsive: true
	});
}
</script>
</html>