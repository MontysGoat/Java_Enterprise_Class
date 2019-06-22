package mod1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBUpdater {
  private static String dbURL = "jdbc:derby:c:\\JHU";
  private static String tableName = "student";
  // jdbc Connection
  private static Connection conn = null;
  private static Statement stmt = null;

  public void UpdateStudents(List<Student> students) {
    createConnection();
    deletePreviousStudents();
    inserStudents(students);
    shutdown();
  }

  private static void createConnection() {
    try {
      Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
      // Get a connection
      conn = DriverManager.getConnection(dbURL);
    } catch (Exception except) {
      except.printStackTrace();
    }
  }

  private void deletePreviousStudents() {
    try{
      stmt = conn.createStatement();
      stmt.execute("DELETE FROM "+tableName);
      stmt.close();
    } catch (SQLException sqlExcept) {
      sqlExcept.printStackTrace();
    }
  }

  private static void inserStudents(List<Student> students) {
    try {
      for (Student student : students) {
        stmt = conn.createStatement();
        stmt.execute("insert into " + tableName + " values ('" 
                                                  + student.firstName + "','" 
                                                  + student.lastName +  "','" 
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

  private static void shutdown() {
    try {
      if (stmt != null) {
        stmt.close();
      }
      if (conn != null) {
        DriverManager.getConnection(dbURL + ";shutdown=true");
        conn.close();
      }
    } catch (SQLException sqlExcept) {

    }
  }

}