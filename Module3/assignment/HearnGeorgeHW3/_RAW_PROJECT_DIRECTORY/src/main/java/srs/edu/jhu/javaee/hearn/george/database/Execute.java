package srs.edu.jhu.javaee.hearn.george.database;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute {

  protected static void query(Connection conn, String query) throws SQLException {
    Statement stmt = conn.createStatement();
    stmt.executeQuery(query);
    stmt.close();
  }

  protected static void update(Connection conn, String update) throws SQLException {
    Statement stmt = conn.createStatement();
    stmt.executeUpdate(update);
    stmt.close();
  }
}