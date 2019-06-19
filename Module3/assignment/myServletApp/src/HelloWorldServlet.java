package edu.jhu.jee.lf;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class HelloWorldServlet extends HttpServlet {
  private String l_name_;
  private String f_name_;

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);
    l_name_ = getInitParameter("last_name");
    f_name_ = getInitParameter("first_name");
  }

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    this.doGet(req, res);
  }

  public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    // set content type and other response header fields first
    res.setContentType("text/html");
    // then write the data of the response
    PrintWriter out = res.getWriter();
    out.println("<br>Hello world! This example is provided by: " + l_name_ + " " + f_name_);
  }

  public String getServletInfo() {
    return "A simple servlet";
  }
}