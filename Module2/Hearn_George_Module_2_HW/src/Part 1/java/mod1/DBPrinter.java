package mod1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class DBPrinter {
  private static String dbURL = "jdbc:derby:c:\\JHU";
  private static String tableName = "student";
  private static String dbName = "c:\\JHU";
  // jdbc Connection
  private static Connection conn = null;
  private static Statement stmt = null;
  String query = "select FIRST_NAME, LAST_NAME, SSN, " + "EMAIL, ADDRESS, USERID, PASSWORD from STUDENT";

  public void PrintStudentsForwardAndBackward() throws SQLException {
    createConnection();
    ResultSet rs = getResults();
    System.out.println("Printing Student In Order: ");
    System.out.println();
    try {
      while (rs.next()) {
        Student student = getStudentRow(rs);
        System.out.println(student.toString());
      }
      System.out.println();
      System.out.println("Printing Student In Reverse Order: ");
      System.out.println();
      while (rs.previous()) {
        Student student = getStudentRow(rs);
        System.out.println(student.toString());
      }
    } finally {
      if (stmt != null)
        stmt.close();
    }
  }

  private static void createConnection() {
    try {
      Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
      // Get a connection
      conn = DriverManager.getConnection(dbURL);
    } catch (Exception except) {
      except.printStackTrace();
    }
  }

  private ResultSet getResults() throws SQLException {
    stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    return stmt.executeQuery(query);
  }

  private Student getStudentRow(ResultSet rs) throws SQLException {
    Student student = new Student();
    student.setFirstName(rs.getString("FIRST_NAME"));
    student.setLastName(rs.getString("LAST_NAME"));
    student.setSsn(rs.getString("SSN"));
    student.setEmail(rs.getString("EMAIL"));
    student.setAddress(rs.getString("ADDRESS"));
    student.setUserID(rs.getString("USERID"));
    student.setPassword(rs.getString("FIRST_NAME"));
    return student;
  }
}