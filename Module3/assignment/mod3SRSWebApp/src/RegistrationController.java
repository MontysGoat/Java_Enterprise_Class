import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class RegistrationController extends HttpServlet {
  public void init(ServletConfig servletConfig) throws ServletException {
    super.init(servletConfig);
 }

 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    
    //this.doGet(req, res);
 }

 public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    // set content type and other response header fields first
    res.setContentType("text/html");

    // then write the data of the response
    PrintWriter out = res.getWriter();
    Enumeration params = req.getParameterNames();
    String paramName = null;
    String[] paramValues = null;

    while (params.hasMoreElements()) {
       paramName = (String) params.nextElement();
       paramValues = req.getParameterValues(paramName);
       out.println("\nParameter name is " + paramName);
       for (int i = 0; i < paramValues.length; i++) {
          out.println(", value " + i + " is " + paramValues[i].toString());
       }
    }
 }

 public String getServletInfo() {
    return "A simple servlet";
 }
}