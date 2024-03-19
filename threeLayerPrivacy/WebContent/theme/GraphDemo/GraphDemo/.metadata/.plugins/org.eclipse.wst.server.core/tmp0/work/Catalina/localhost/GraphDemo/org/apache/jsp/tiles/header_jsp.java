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
import com.beans.UserModel;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<header class=\"main-header\">\r\n");
      out.write("    <!-- Logo -->\r\n");
      out.write("    <a href=\"#\" class=\"logo\">\r\n");
      out.write("      <!-- mini logo for sidebar mini 50x50 pixels -->\r\n");
      out.write("      <span class=\"logo-mini\"><b>D</b>GI</span>\r\n");
      out.write("      <!-- logo for regular state and mobile devices -->\r\n");
      out.write("      <span class=\"logo-lg\"><b>Gasoline</b>Indicator</span>\r\n");
      out.write("    </a>\r\n");
      out.write("    <!-- Header Navbar: style can be found in header.less -->\r\n");
      out.write("    <nav class=\"navbar navbar-static-top\">\r\n");
      out.write("      <!-- Sidebar toggle button-->\r\n");
      out.write("      <a href=\"#\" class=\"sidebar-toggle\" data-toggle=\"push-menu\" role=\"button\">\r\n");
      out.write("        <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("      </a>\r\n");
      out.write("\r\n");
      out.write("      <div class=\"navbar-custom-menu\">\r\n");
      out.write("        <ul class=\"nav navbar-nav\" >\r\n");

  	String name="User";
 	UserModel um = (UserModel) session.getAttribute("USER_MODEL");
	if (um != null){
		name=um.getFname();

      out.write("        \r\n");
      out.write("          <!-- User Account: style can be found in dropdown.less -->\r\n");
      out.write("          <li class=\"dropdown user user-menu\">\r\n");
      out.write("            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\" style=\"height: 50px;\">\r\n");
      out.write("              <img src=\"");
      out.print(request.getContextPath());
      out.write("/theme/dist/img/profile.jpg\" class=\"user-image\" alt=\"User Image\">\r\n");
      out.write("              <span class=\"hidden-xs\">");
      out.print(name );
      out.write("</span>\r\n");
      out.write("            </a>\r\n");
      out.write("            <ul class=\"dropdown-menu\">\r\n");
      out.write("              <!-- User image -->\r\n");
      out.write("              <li class=\"user-header\">\r\n");
      out.write("                <img src=\"");
      out.print(request.getContextPath());
      out.write("/theme/dist/img/profile.jpg\" class=\"img-circle\" alt=\"User Image\">\r\n");
      out.write("\r\n");
      out.write("                <p>");
      out.print(um.getFname() + " " + um.getLname() );
      out.write("\r\n");
      out.write("                  <small>Member</small>\r\n");
      out.write("                </p>\r\n");
      out.write("              </li>\r\n");
      out.write("              <!-- Menu Footer-->\r\n");
      out.write("              <li class=\"user-footer\">\r\n");
      out.write("                <div class=\"pull-left\">\r\n");
      out.write("                  <a href=\"#\" class=\"btn btn-default btn-flat\">Profile</a>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"pull-right\">\r\n");
      out.write("                  <a href=\"");
      out.print(request.getContextPath());
      out.write("/tiles/ajax.jsp?methodId=logout\" class=\"btn btn-default btn-flat\">Sign out</a>\r\n");
      out.write("                </div>\r\n");
      out.write("              </li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </li>\r\n");

		}

      out.write("          \r\n");
      out.write("        </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("    </nav>\r\n");
      out.write("  </header>");
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