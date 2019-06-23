package srs.servlet;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Container;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import srs.bean.*;
import srs.database.*;

public class RegistrationController extends HttpServlet {
   private String dataSourceName;
   private String wlsURL;

   public void init(ServletConfig servletConfig) throws ServletException {
      super.init(servletConfig);
      dataSourceName = getInitParameter("data_source_name");
      wlsURL = getInitParameter("web_logic_server_address");
   }

   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      PrintWriter out = response.getWriter();
      Enumeration params = request.getParameterNames();
      String paramName = null;
      List<String> paramValues = null;

      while (params.hasMoreElements()) {
         paramName = (String) params.nextElement();
         paramValues = Arrays.asList(request.getParameterValues(paramName));
         if (paramName.equals("login") && paramValues.contains("Login")) {
            RequestDispatcher rd = request.getRequestDispatcher("login");
            rd.forward(request, response);
            break;
         } else if (paramName.equals("linkToRegistration") && paramValues.contains("Register")) {
            out.println("GoToRegistration");
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
            break;
         } else if (paramName.equals("continueRegistration") && paramValues.contains("Continue")) {
            continueRegistration(request, response);
            break;
         } else if (paramName.equals("finishRegistration") && paramValues.contains("Register")) {
            finishRegistration(request, response);
            break;
         }
      }

   }

   public void continueRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {

      String userName = request.getParameter("user_id");
      String password = request.getParameter("password");
      String password_repeat = request.getParameter("password_repeat");
      String first_name = request.getParameter("first_name");
      String last_name = request.getParameter("last_name");
      String ssn = request.getParameter("ssn");
      String email = request.getParameter("email");
      PrintWriter out = response.getWriter();

      boolean inputIsValid = true;
      try {
         Student registeredStudent = getRegisteredStudent(userName);
         inputIsValid = UserNameIsValid(userName, registeredStudent, request) && inputIsValid;
         inputIsValid = PasswordIsValid(password, request) && inputIsValid;
         inputIsValid = PasswordRepeatIsValid(password, password_repeat, request) && inputIsValid;
         inputIsValid = FirstNameIsValid(first_name, request) && inputIsValid;
         inputIsValid = LastNameIsValid(last_name, request) && inputIsValid;
         inputIsValid = SsnIsValid(ssn, request) && inputIsValid;
         inputIsValid = EmailIsValid(email, request) && inputIsValid;
         request.setAttribute("isValid", inputIsValid);
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
         rd.forward(request, response);
      } catch (NamingException e) {
         out.println("Could not connect to database server. Try again later.");// TO-DO: change to HTTP error page
      } catch (SQLException e) {
         out.println("Database schema is corrupted.");// TO-DO: change to HTTP error page
      } catch (ServletException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private boolean EmailIsValid(String email, HttpServletRequest request) {
      if(email == null || email.trim().equals("")){
         request.getSession().setAttribute("email_error", "Required field");
         return false;
      }
      return true;
   }

   private boolean SsnIsValid(String ssn, HttpServletRequest request) {
      if(ssn == null || ssn.trim().equals("")){
         request.getSession().setAttribute("ssn_error", "Required field");
         return false;
      }
      return true;
   }

   private boolean LastNameIsValid(String last_name, HttpServletRequest request) {
      if(last_name == null || last_name.trim().equals("")){
         request.getSession().setAttribute("last_name_error", "Required field");
         return false;
      }
      return true;
   }

   private boolean FirstNameIsValid(String first_name, HttpServletRequest request) {
      if(first_name == null || first_name.trim().equals("")){
         request.getSession().setAttribute("first_name_error", "Required field");
         return false;
      }
      return true;
   }

   private boolean PasswordRepeatIsValid(String password, String password_repeat, HttpServletRequest request) {
      if(!password.equals(password_repeat)){
         request.getSession().setAttribute("password_repeat_error", "Passwords must match");
         return false;
      }
      return true;
   }

   private boolean UserNameIsValid(String userName, Student registeredStudent, HttpServletRequest request) {
      if (registeredStudent != null) {
         request.getSession().setAttribute("user_id_error", "User Name already taken.");
         return false;
      } else if (userName == null || userName.trim() == "" || userName.length() < 7) {
         request.getSession().setAttribute("user_id_error", "User Name must be at least 7 characters.");
         return false;
      }
      return true;
   }

   private boolean PasswordIsValid(String password, HttpServletRequest request) {
      if (password == null || password.trim() == "" || password.length() < 7) {
         request.getSession().setAttribute("password_error", "Password must be at least 7 characters.");
         return false;
      }
      return true;
   }

   public void finishRegistration(HttpServletRequest request, HttpServletResponse response) {

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

   public String getServletInfo() {
      return "A simple servlet";
   }
}