/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.39
 * Generated at: 2018-01-19 13:03:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.tiles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.database.ConnectionManager;
import com.beans.UserModel;

public final class left_005fsidebar_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- Left side column. contains the logo and sidebar -->\r\n");
      out.write("  <aside class=\"main-sidebar\">\r\n");
      out.write("    <!-- sidebar: style can be found in sidebar.less -->\r\n");
      out.write("    <section class=\"sidebar\">\r\n");

	String name="User";
	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
	if (um != null){
		name=um.getFname() + " " + um.getLname();

      out.write("      \r\n");
      out.write("      <!-- Sidebar user panel -->\r\n");
      out.write("      <div class=\"user-panel\">\r\n");
      out.write("        <div class=\"pull-left image\">\r\n");
      out.write("          <img src=\"");
      out.print(request.getContextPath());
      out.write("/theme/dist/img/profile.jpg\" class=\"img-circle\" alt=\"User Image\">\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"pull-left info\">\r\n");
      out.write("          <p>");
      out.print(name);
      out.write("</p>\r\n");
      out.write("          <a href=\"#\"><i class=\"fa fa-circle text-success\"></i> Online</a>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("\r\n");
      out.write("      <!-- sidebar menu: : style can be found in sidebar.less -->\r\n");
      out.write("      <ul class=\"sidebar-menu\" data-widget=\"tree\">\r\n");
      out.write("        <li class=\"header\">MAIN NAVIGATION</li>\r\n");
      out.write("        <li>\r\n");
      out.write("          <a href=\"");
      out.print(request.getContextPath());
      out.write("/pages/index.jsp\">\r\n");
      out.write("            <i class=\"fa fa-dashboard\"></i> <span>Dashboard</span>\r\n");
      out.write("          </a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li>\r\n");
      out.write("        \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/pages/registerVehicle.jsp\">\r\n");
      out.write("        \t\t<i class=\"fa fa-fw fa-bus\"></i> \r\n");
      out.write("        \t\t<span>Vehicle Registration</span>\r\n");
      out.write("        \t</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li>\r\n");
      out.write("        \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/pages/graphs.jsp\">\r\n");
      out.write("        \t\t<i class=\"fa fa-fw fa-hourglass-o\"></i> \r\n");
      out.write("        \t\t<span>View Graphs</span>\r\n");
      out.write("        \t</a>\r\n");
      out.write("        </li>\r\n");
      out.write("<!--         <li class=\"treeview\"> -->\r\n");
      out.write("<!--           <a href=\"#\"> -->\r\n");
      out.write("<!--             <i class=\"fa fa-fw fa-hourglass-o\"></i> <span>Fuel Quantity</span> -->\r\n");
      out.write("<!--             <span class=\"pull-right-container\"> -->\r\n");
      out.write("<!--               <i class=\"fa fa-angle-left pull-right\"></i> -->\r\n");
      out.write("<!--             </span> -->\r\n");
      out.write("<!--           </a>        -->\r\n");
      out.write("<!--           <ul class=\"treeview-menu\"> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t            <i class=\"fa fa-fw fa-hourglass-end\"></i>Filled Quantity</a></li> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t            <i class=\"fa fa-fw fa-hourglass-half\"></i>Refilled Quantity</a></li> -->\r\n");
      out.write("<!--           </ul> -->\r\n");
      out.write("<!--         </li>\t  -->\r\n");
      out.write("        <li>\r\n");
      out.write("        \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/pages/fuelstatus.jsp\">\r\n");
      out.write("        \t\t<i class=\"fa fa-fw fa-file-text-o\"></i> \r\n");
      out.write("        \t\t<span>Fuel Status</span>\r\n");
      out.write("        \t</a>\r\n");
      out.write("        </li>\r\n");
      out.write("        <li>\r\n");
      out.write("        \t<a href=\"");
      out.print(request.getContextPath());
      out.write("/pages/reports.jsp\">\r\n");
      out.write("<!--         \t\t<i class=\"fa fa-fw fa-envelope\"></i> -->\r\n");
      out.write("        \t\t<i class=\"fa fa-fw fa-file-text-o\"></i>  \r\n");
      out.write("        \t\t<span>Reports</span>\r\n");
      out.write("        \t</a>\r\n");
      out.write("        </li>\t  \r\n");
      out.write("      </ul>  \r\n");
		
	}

      out.write("          \r\n");
      out.write("    </section>\r\n");
      out.write("    <!-- /.sidebar -->\r\n");
      out.write("  </aside>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}