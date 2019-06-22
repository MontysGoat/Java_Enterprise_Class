package mod1;

import javax.naming.*;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class JNDILookerUpper {
  private static Connection conn = null;
  private static Statement stmt = null;

  public void lookup() {
    Context ctx = null;
    try {
      String url = null;
      Hashtable env = new Hashtable();
      env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
      env.put(Context.PROVIDER_URL, "t3://localhost:7001");
      env.put(Context.SECURITY_PRINCIPAL, "weblogic");
      env.put(Context.SECURITY_CREDENTIALS, "P4$sword");

      ctx = new InitialContext(env);
      javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jhuDataSource");
      conn = ds.getConnection();
      stmt = conn.createStatement();
      stmt.execute("select * from STUDENT");
      ResultSet rs = stmt.getResultSet();

      System.out.println("Printing Student In Order: ");
      System.out.println();
      try {
        while (rs.next()) {
          Student student = getStudentRow(rs);
          System.out.println(student.toString());
        }
        System.out.println();
      } finally {
        if (stmt != null)
          stmt.close();
      }

    } catch (NamingException e) {
      System.out.println(e.toString());
    } catch (SQLException e) {
      e.printStackTrace();
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