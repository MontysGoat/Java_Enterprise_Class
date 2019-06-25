package srs.edu.jhu.javaee.hearn.george.servlet;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


import srs.edu.jhu.javaee.hearn.george.bean.*;
import srs.edu.jhu.javaee.hearn.george.database.*;

public class RegistrationController extends HttpServlet {
   private String dataSourceName;
   private String wlsURL;
   private int classSizeLimit;

   public void init(ServletConfig servletConfig) throws ServletException {
      super.init(servletConfig);
      dataSourceName = getInitParameter("data_source_name");
      wlsURL = getInitParameter("web_logic_server_address");
      System.out.println("WLSURL is " + wlsURL);
      classSizeLimit = Integer.parseInt(getInitParameter("classSizeLimit"));
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
            HttpSession session = request.getSession(false);
            if (session != null) {
               session.invalidate();
            }
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
            rd.forward(request, response);
            break;
         } else if (paramName.equals("continueRegistration") && paramValues.contains("Continue")) {
            continueRegistration(request, response);
            break;
         } else if (paramName.equals("finishRegistration") && paramValues.contains("Register")) {
            System.out.println("Going to finishRegistration.");
            finishRegistration(request, response);
            break;
         } else if (paramName.equals("welcome") && paramValues.contains("class_register")) {
            RequestDispatcher rd = request.getRequestDispatcher("registrar");
            rd.forward(request, response);
            break;
         } else if (paramName.equals("welcome") && paramValues.contains("logout")) {
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.include(request, response);
            break;
         } else if (paramName.equals("availableCourses")) {
            try {
               if (courseIsAvailable(paramValues.get(0))) {
                  out.println("Successfully Registered!");
               } else {
                  out.println("No more spaces available in that course.");
               }
            } catch (NamingException e) {
               out.println("Unable to connect to database. Details: " + e.getMessage());
            } catch (SQLException e) {
               out.println("Could not execute database update. Details: " + e.getMessage() + e.getStackTrace());
            }
         }
      }

   }

   private boolean courseIsAvailable(String paramValue) throws NamingException, SQLException {
      Connection conn;
      int courseId = Integer.parseInt(paramValue);
      conn = ConnectionSupplier.getConnection(dataSourceName, wlsURL);
      List<Registrar> registrars = (List<Registrar>) Lookup.registrars(conn);
      System.out.println("Got registrars");
      Registrar registrar = registrars.stream().filter(r -> r.getCourseId() == courseId).findFirst().orElse(null);
      System.out.println("registrar: " + registrar.getCourseId());
      if (registrar == null) {
         return false;
      } else {
         int numberRegistered = registrar.getNumber_students_registered();
         System.out.println("number registered: " + numberRegistered);
         if (registrar.getNumber_students_registered() >= classSizeLimit) {
            return false;
         } else {
            registrar.setNumber_students_registered(numberRegistered + 1);
            System.out.println(numberRegistered + 1);
            Update.registrar(conn, registrar);
            return true;
         }
      }

   }

   private void continueRegistration(HttpServletRequest request, HttpServletResponse response) throws IOException {
      Student newStudent = getStudentFromRequest(request);
      String password_repeat = request.getParameter("password_repeat");
      PrintWriter out = response.getWriter();
      try {
         validateInput(newStudent, password_repeat, request);
         setSessionStudent(newStudent, password_repeat, request);
         request.getSession().setAttribute("newStudent", newStudent);
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/register.jsp");
         rd.forward(request, response);
      } catch (NamingException e) {
         out.println("Could not connect to database server. Try again later. Further Details: " + e.getExplanation()
               + e.getMessage() + e.getCause());// TO-DO: change to HTTP error page
      } catch (SQLException e) {
         out.println("Database schema is corrupted. Details: " + e.getMessage() + e.getStackTrace());// TO-DO: change to HTTP error page
      } catch (ServletException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   private void finishRegistration(HttpServletRequest request, HttpServletResponse response) {

      System.out.println("Finishing Registration.");
      Student newStudent = (Student) request.getSession().getAttribute("newStudent");
      newStudent.setAddress(getAddressFromRequest(request));
      System.out.println(newStudent.toString());
      System.out.println("New student object created.");
      PrintWriter out = null;
      try {
         out = response.getWriter();
         System.out.println("New student object created");
         Connection conn = ConnectionSupplier.getConnection(dataSourceName, wlsURL);
         System.out.println("Connection created. Attempting to insert new student.");
         Insert.student(conn, newStudent);
         System.out.println("New student inserted successfully.");
         HttpSession session = request.getSession(true);
         System.out.println("Welcoming student: " + newStudent.getfirst_name() + " " + newStudent.getLast_name());
         request.getSession().setAttribute("first_name", newStudent.getfirst_name());
         request.getSession().setAttribute("last_name", newStudent.getLast_name());
         RequestDispatcher rd = getServletContext().getRequestDispatcher("/welcome.jsp");
         rd.forward(request, response);
      } catch (NamingException e) {
         out.println("Could not connect to database server. Try again later. Further Details: " + e.getExplanation()
               + e.getMessage() + e.getCause());// TO-DO: change to HTTP error page
      } catch (SQLException e) {
         out.println("Database schema is corrupted." + e.getMessage() + e.getCause());// TO-DO: change to HTTP error
                                                                                      // page
      } catch (IOException e) {
         out.println("Could not create writer.");// TO-DO: change to HTTP error page
      } catch (ServletException e) {
         out.println("Could not forward the page" + e.getMessage());
      }
   }

   private void setSessionStudent(Student newStudent, String password_repeat, HttpServletRequest request) {
      request.getSession().setAttribute("user_id", newStudent.getUserID());
      request.getSession().setAttribute("password", newStudent.getPassword());
      request.getSession().setAttribute("password_repeat", password_repeat);
      request.getSession().setAttribute("first_name", newStudent.getfirst_name());
      request.getSession().setAttribute("last_name", newStudent.getLast_name());
      request.getSession().setAttribute("ssn", newStudent.getSsn());
      request.getSession().setAttribute("email", newStudent.getEmail());
   }

   private void validateInput(Student newStudent, String password_repeat, HttpServletRequest request)
         throws NamingException, SQLException {
      boolean inputIsValid = true;
      Student registeredStudent = getRegisteredStudent(newStudent.getUserID());
      inputIsValid = userNameIsValid(newStudent.getUserID(), registeredStudent, request) && inputIsValid;
      inputIsValid = passwordIsValid(newStudent.getPassword(), request) && inputIsValid;
      inputIsValid = passwordRepeatIsValid(newStudent.getPassword(), password_repeat, request) && inputIsValid;
      inputIsValid = firstNameIsValid(newStudent.getfirst_name(), request) && inputIsValid;
      inputIsValid = LastNameIsValid(newStudent.getLast_name(), request) && inputIsValid;
      inputIsValid = ssnIsValid(newStudent.getSsn(), request) && inputIsValid;
      inputIsValid = emailIsValid(newStudent.getEmail(), request) && inputIsValid;
      request.setAttribute("isValid", inputIsValid);
   }

   private Student getStudentFromRequest(HttpServletRequest request) {
      Student newStudent = new Student();
      newStudent.setUserID(request.getParameter("user_id"));
      newStudent.setPassword(request.getParameter("password"));
      newStudent.setfirst_name(request.getParameter("first_name"));
      newStudent.setLast_name(request.getParameter("last_name"));
      newStudent.setSsn(request.getParameter("ssn"));
      newStudent.setEmail(request.getParameter("email"));
      return newStudent;
   }

   private String getAddressFromRequest(HttpServletRequest request) {
      String street_address = request.getParameter("street_address");
      street_address = (street_address != null) ? street_address : "";
      String city_state = request.getParameter("city_state");
      city_state = (city_state != null) ? city_state : "";
      String zip_code = request.getParameter("zip_code");
      zip_code = (zip_code != null) ? zip_code : "";
      return street_address + " " + city_state + " " + zip_code;
   }

   private boolean emailIsValid(String email, HttpServletRequest request) {
      if (email == null || email.trim().equals("")) {
         request.getSession().setAttribute("email_error", "Required field");
         return false;
      }
      request.getSession().setAttribute("email_error", "");
      return true;
   }

   private boolean ssnIsValid(String ssn, HttpServletRequest request) {
      if (ssn == null || ssn.trim().equals("")) {
         request.getSession().setAttribute("ssn_error", "Required field");
         return false;
      }
      request.getSession().setAttribute("ssn_error", "");
      return true;
   }

   private boolean LastNameIsValid(String last_name, HttpServletRequest request) {
      if (last_name == null || last_name.trim().equals("")) {
         request.getSession().setAttribute("last_name_error", "Required field");
         return false;
      }
      request.getSession().setAttribute("last_name_error", "");
      return true;
   }

   private boolean firstNameIsValid(String first_name, HttpServletRequest request) {
      if (first_name == null || first_name.trim().equals("")) {
         request.getSession().setAttribute("first_name_error", "Required field");
         return false;
      }
      request.getSession().setAttribute("first_name_error", "");
      return true;
   }

   private boolean passwordRepeatIsValid(String password, String password_repeat, HttpServletRequest request) {
      if (!password.equals(password_repeat)) {
         request.getSession().setAttribute("password_repeat_error", "Passwords must match");
         return false;
      }
      request.getSession().setAttribute("password_repeat_error", "");
      return true;
   }

   private boolean userNameIsValid(String userName, Student registeredStudent, HttpServletRequest request) {
      if (registeredStudent != null) {
         request.getSession().setAttribute("user_id_error", "User Name already taken.");
         return false;
      } else if (userName == null || userName.trim() == "" || userName.length() < 7) {
         request.getSession().setAttribute("user_id_error", "User Name must be at least 7 characters.");
         return false;
      }
      request.getSession().setAttribute("user_id_error", "");
      return true;
   }

   private boolean passwordIsValid(String password, HttpServletRequest request) {
      if (password == null || password.trim() == "" || password.length() < 7) {
         request.getSession().setAttribute("password_error", "Password must be at least 7 characters.");
         return false;
      }
      request.getSession().setAttribute("password_error", "");
      return true;
   }

   private Student getRegisteredStudent(String userName) throws NamingException, SQLException {
      Collection<Student> registeredStudents = getRegisteredStudents();
      return registeredStudents.stream().filter(s -> s.getUserID().equals(userName)).findFirst().orElse(null);
   }

   private Collection<Student> getRegisteredStudents() throws NamingException, SQLException {
      try (Connection conn = ConnectionSupplier.getConnection(dataSourceName, wlsURL)) {
         return Lookup.students(conn);
      }
   }

   public String getServletInfo() {
      return "A Registration servlet";
   }
}