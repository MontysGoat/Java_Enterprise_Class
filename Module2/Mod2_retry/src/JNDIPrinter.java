

import javax.naming.*;
import javax.sql.DataSource;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JNDIPrinter {
  private static String tableName = "student";
  // jdbc Connection
  private static Connection conn = null;
  private static Statement stmt = null;

  public void AddStudents() {
    Context ctx = null;
    try {
      conn = GetConnection();
      List<Student> students;

      students = new ArrayList<Student>(
          Arrays.asList(new Student("first1", "last1", "111-11-1111", "1@1.com", "111 1st st", "user1", "pw1"),
              new Student("first2", "last2", "222-22-2222", "2@2.com", "222 2nd st", "user2", "pw2"),
              new Student("first3", "last3", "333-33-3333", "3@3.com", "333 3rd st", "user3", "pw3")));

      InsertStudents(students);
    } catch (Exception e) {
      System.out.println(e.toString());
    } finally {
      if (ctx != null) {
        try {
          ctx.close();
        } catch (NamingException e) {
          System.out.println("Failed to close context due to: " + e);
        }
      }
    }
  }

  private Connection GetConnection() throws NamingException, SQLException {
    Context ctx = null;
    String url = null;
    System.out.println(url);
    Hashtable env = new Hashtable();
    // This *required* property specifies the factory to be used
    // to create the context.
    env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");

    if (url != null) {
      // This property specifies the URL of the WebLogic Server that will
      // provide the naming service. Defaults to t3://localhost:7001
      env.put(Context.PROVIDER_URL, url);
    }

    ctx = new InitialContext(env);
    System.out.println("Initial context created");
    DataSource ds = (javax.sql.DataSource) ctx.lookup("jhuDataSource");
    System.out.println("Datasource created");
    java.sql.Connection connection = (java.sql.Connection) ds.getConnection();
    return connection;
  }

  private void InsertStudents(List<Student> students) throws SQLException{
    try {
      for (Student student : students) {
        stmt = conn.createStatement();
        stmt.execute("insert into " + tableName + " values ('" 
                                                  + student.first_name + "','" 
                                                  + student.last_name +  "','" 
                                                  + student.ssn +       "','"
                                                  + student.email +     "','" 
                                                  + student.address +   "','" 
                                                  + student.userID +    "','" 
                                                  + student.password +  "')");
        stmt.close();
      }
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  }
}