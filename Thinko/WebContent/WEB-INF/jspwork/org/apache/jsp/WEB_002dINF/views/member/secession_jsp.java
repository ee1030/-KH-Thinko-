/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.61
 * Generated at: 2021-01-15 07:54:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class secession_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/views/member/../common/footer.jsp", Long.valueOf(1610673735276L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>회원 탈퇴</title>\r\n");
      out.write("    <link rel=\"stylesheet\" type=\"text/css\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/css/member.css\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"container\" id=\"content-main\">\r\n");
      out.write("\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../common/header.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<div class=\"row my-5\">\r\n");
      out.write("\t\t\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "memberSideMenu.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t<div class=\"col-sm-offset-2 col-sm-8\">\r\n");
      out.write("\t\t\t\t<h5 id=content>S E C E S S I O N</h5>\r\n");
      out.write("\t\t\t\t<hr>\r\n");
      out.write("\t\t\t\t<br>\r\n");
      out.write("\t\t\t\t<div class=\"bg-white rounded shadow-sm container p-3\">\r\n");
      out.write("\t\t\t\t\t<form method=\"POST\" action=\"updateStatus.do\"\r\n");
      out.write("\t\t\t\t\t\tonsubmit=\"return secessionValidate();\" class=\"form-horizontal\"\r\n");
      out.write("\t\t\t\t\t\trole=\"form\">\r\n");
      out.write("\t\t\t\t\t\t<!-- 아이디 -->\r\n");
      out.write("\t\t\t\t\t\t<div class=\"row mb-3 form-row\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<h6>아이디</h6>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<h5 id=\"id\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${loginMember.memberId }", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("</h5>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<!-- 비밀번호 -->\r\n");
      out.write("\t\t\t\t\t\t<div class=\"mb-3 form-row\">\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"infoList col-md-3\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<label for=\"currentPwd\">비밀번호</label>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"inputDiv col-md-6\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<input type=\"password\" class=\"inputBox\" id=\"currentPwd\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tname=\"currentPwd\" autocomplete=\"off\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\tplaceholder=\"회원 비밀번호를 입력해주세요.\" maxlength=\"12\">\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t<hr>\r\n");
      out.write("\t\t\t\t\t\t<div class=\"panel panel-default\">\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t\t\t\t<div class=\"panel-body\">\r\n");
      out.write("\t\t\t\t\t\t\t\t<div class=\"form-group pull-left\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<label class=\"control-label\"> 회원 탈퇴 약관 </label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"col-xs-12\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<textarea class=\"form-control\" readonly rows=\"10\" cols=\"100\">\r\n");
      out.write("제1조\r\n");
      out.write("이 약관은 샘플 약관입니다.\r\n");
      out.write("\r\n");
      out.write("① 약관 내용 1\r\n");
      out.write("\r\n");
      out.write("② 약관 내용 2\r\n");
      out.write("\r\n");
      out.write("③ 약관 내용 3\r\n");
      out.write("\r\n");
      out.write("④ 약관 내용 4\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("제2조\r\n");
      out.write("이 약관은 샘플 약관입니다.\r\n");
      out.write("\r\n");
      out.write("① 약관 내용 1\r\n");
      out.write("\r\n");
      out.write("② 약관 내용 2\r\n");
      out.write("\r\n");
      out.write("③ 약관 내용 3\r\n");
      out.write("\r\n");
      out.write("④ 약관 내용 4\r\n");
      out.write("</textarea>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<div class=\"checkbox pull-right\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<div class=\"custom-checkbox\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<div class=\"form-check\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"agree\" id=\"agree\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"form-check-input custom-control-input\"> <br>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<label class=\"form-check-label custom-control-label\"\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tfor=\"agree\">위 약관에 동의합니다.</label>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t\t<br> <br>\r\n");
      out.write("\t\t\t\t\t\t<button id=\"btn\" class=\"btn btn-outline-light btn-lg btn-block\"\r\n");
      out.write("\t\t\t\t\t\t\ttype=\"submit\">회원 탈퇴</button>\r\n");
      out.write("\t\t\t\t\t</form>\r\n");
      out.write("\t\t\t\t</div>\r\n");
      out.write("\t\t\t</div>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\r\n");
      out.write("\r\n");
      out.write("<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\">\r\n");
      out.write("    <link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400&display=swap\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("<style>\r\n");
      out.write("\r\n");
      out.write("   *{\r\n");
      out.write("      font-family: 'Noto Sans KR', sans-serif;\r\n");
      out.write("      color: rgb(37, 37, 37);\r\n");
      out.write("   }\r\n");
      out.write("   \r\n");
      out.write("   .m-0>ul{\r\n");
      out.write("      list-style-type: none;\r\n");
      out.write("      \r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("   .m-0 li{\r\n");
      out.write("      float: left;\r\n");
      out.write("      margin-left: 30px;\r\n");
      out.write("      font-size: 16px;\r\n");
      out.write("      \r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("   .footer{\r\n");
      out.write("      background-color: #7777;\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("   #footer-text1{\r\n");
      out.write("      font-weight: bolder;\r\n");
      out.write("   }\r\n");
      out.write("   \r\n");
      out.write("   .m-0>ul>li:nth-of-type(2n){\r\n");
      out.write("      color: #9999;\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("   .m-0 li>a{\r\n");
      out.write("      color: rgb(37, 37, 37);\r\n");
      out.write("      text-decoration: none;\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("   <div class=\"py-4 footer\">\r\n");
      out.write("     <div class=\"container\">\r\n");
      out.write("       <div id=\"footer-text1\" class=\"m-0 text-center text-black\">\r\n");
      out.write("         <ul>\r\n");
      out.write("            <li><a href=\"#\">이용약관</a></li>\r\n");
      out.write("            <li>|</li>\r\n");
      out.write("            <li><a href=\"#\">개인정보 취급방침</a></li>\r\n");
      out.write("            <li>|</li>\r\n");
      out.write("            <li><a href=\"#\">도움말</a></li>\r\n");
      out.write("         </ul>\r\n");
      out.write("      </div>\r\n");
      out.write("      <br>\r\n");
      out.write("       <div id=\"footer-text2\" class=\"m-0 text-center text-balck\">\r\n");
      out.write("         <ul>\r\n");
      out.write("            <li><a href=\"#\">(주) 종로의 객체들 </a></li>\r\n");
      out.write("            <li>|</li>\r\n");
      out.write("            <li><a href=\"#\">대표이사 : 정연정</a></li>\r\n");
      out.write("            <li>|</li>\r\n");
      out.write("            <li><a href=\"#\">문의전화 : 7777-8888</a></li>\r\n");
      out.write("         </ul>\r\n");
      out.write("         <br>\r\n");
      out.write("      </div>\r\n");
      out.write("     </div>\r\n");
      out.write("   </div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null));
      out.write("/resources/js/member.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}