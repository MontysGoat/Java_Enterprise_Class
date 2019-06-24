package srs.servlet;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.Container;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import srs.bean.*;
import srs.database.*;

public class RegistrarController extends HttpServlet {
  private String dataSourceName;
  private String wlsURL;

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);
    dataSourceName = getInitParameter("data_source_name");
    wlsURL = getInitParameter("web_logic_server_address");
    System.out.println("WLSURL is " + wlsURL);
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    try {
      Collection<Course> availableCourses = getAvailableCourses();
      request.setAttribute("availableCourses", availableCourses);
      RequestDispatcher rd = request.getRequestDispatcher("/courses.jsp");
      rd.include(request, response);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } catch (NamingException e) {
      System.out.println(e.getMessage());
    }
  }

  private Collection<Course> getAvailableCourses() throws SQLException, NamingException {
    try (Connection conn = ConnectionSupplier.getConnection(dataSourceName, wlsURL)) {
      return Lookup.courses(conn);
    }
  }
}