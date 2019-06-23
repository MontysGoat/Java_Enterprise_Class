package srs.servlet;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;

import srs.bean.*;
import srs.database.*;

public class LoginController extends HttpServlet {

  private String dataSourceName;
  private String wlsURL;
  private int loginAttemptLimit;
  private Hashtable<String, Integer> loginAttempts = new Hashtable<String, Integer>();

  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);
    dataSourceName = getInitParameter("data_source_name");
    wlsURL = getInitParameter("web_logic_server_address");
    loginAttemptLimit = Integer.parseInt(getInitParameter("login_attempt_limit"));
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String userName = request.getParameter("user_id");
    String password = request.getParameter("password");

    try {
      Student registeredStudent = getRegisteredStudent(userName);

      if (registeredStudent == null) {
        out.print("Sorry, you don't have an account. You must register first.");
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.include(request, response);
      } else 
      if (hasExceededLoginLimit(userName)) {
        out.print("Login attempts exceeded limit. Locked out.");
        RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
        rd.include(request, response);
      } else {
        if (registeredStudent.getPassword().equals(password)) {
          loginAttempts.remove(userName);
          request.getSession().setAttribute("first_name", registeredStudent.getfirst_name());
          request.getSession().setAttribute("last_name", registeredStudent.getLast_name());
          RequestDispatcher rd = getServletContext().getRequestDispatcher("/welcome.jsp");
          rd.forward(request, response);
        } else {
          out.print("Sorry UserName or Password do not match!");
          RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
          rd.include(request, response);
        }
      }
    } catch (NamingException e) {
      out.println("Could not connect to database server. Try again later.");// TO-DO: change to HTTP error page
    } catch (SQLException e) {
      out.println("Database schema is corrupted.");// TO-DO: change to HTTP error page
    }
  }

  private boolean hasExceededLoginLimit(String userName) {
    if (loginAttempts.containsKey(userName)) {
      Integer attemptCount = loginAttempts.get(userName) + 1;
      if (attemptCount >= loginAttemptLimit) {
        return true;
      } else {
        loginAttempts.put(userName, attemptCount);
        return false;
      }
    } else {
      loginAttempts.put(userName, 1);
      return false;
    }
  }

  private Student getRegisteredStudent(String userName) throws NamingException, SQLException {
    Collection<Student> registeredStudents = GetRegisteredStudents();
    return registeredStudents.stream().filter(s -> s.getUserID().equals(userName)).findFirst().orElse(null);
  }

  private Collection<Student> GetRegisteredStudents() throws NamingException, SQLException {
    try (Connection conn = ConnectionSupplier.GetConnection(dataSourceName, wlsURL)) {
      return Lookup.Students(conn);
    }
  }
}