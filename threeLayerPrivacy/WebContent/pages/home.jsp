<!DOCTYPE html>
<%@page import="com.helper.UserModel"%>
<html lang="en">

<head>
<jsp:include page="../tiles/inc.jsp"></jsp:include>
</head>
<%UserModel um=(UserModel)session.getAttribute("USER_MODEL");%>
<body id="page-top" class="index">
<jsp:include page="../tiles/menu.jsp"></jsp:include>
 
 <!-- Contact Section --> 
    <section id="contact">
        <div class="container">
        <br><br><br><br><br><br><br><br><br>
            <div class="row">
                <div class="col-lg-12 text-center">
                <%if(um!=null){ %>
                    <h2 class="section-heading">Welcome <%=um.getName()%></h2>   
                    <%}else{ %>
                     <h2 class="section-heading">Welcome</h2>
                     <%} %>
                    <h3 class="section-subheading text-muted">Providing Security with Distributed Storage & Peace of Mind for You</h3>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12 text-center">
                </div>
            </div>
        </div>
    </section>
    <jsp:include page="../tiles/footer.jsp"></jsp:include>
</body>
</html>
