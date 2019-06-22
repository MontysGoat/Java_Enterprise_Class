package srs.servlet;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import srs.bean.*;
import srs.database.*;


public class LoginController extends HttpServlet {
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    String userName = request.getParameter("user_id");
    String password = request.getParameter("password");

    try {
      if(studentIsRegistered(userName, password)){
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/welcome.jsp");
        rd.forward(request,response);
      } else {
        out.print("Sorry UserName or Password Error!");  
        RequestDispatcher rd=request.getRequestDispatcher("/login.jsp");  
        rd.include(request, response); 
      }
    } catch (NamingException e) {
      out.println("Could not connect to database server. Try again later.");//TO-DO: change to HTTP error page

    } catch (SQLException e) {
      out.println("Database schema is corrupted.");//TO-DO: change to HTTP error page
    }
  }

  private boolean studentIsRegistered(String userName, String password) throws NamingException, SQLException {    
    Collection<Student> registeredStudents = GetRegisteredStudents();
    return registeredStudents.stream().anyMatch(s -> s.getUserID().equals(userName) && s.getPassword().equals(password));
  }

  private Collection<Student> GetRegisteredStudents() throws NamingException, SQLException {
    Connection conn = ConnectionSupplier.GetConnection("jhuDataSource", null);
    return Lookup.Students(conn);
  }
}