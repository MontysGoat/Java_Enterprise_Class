package srs.database;

import javax.naming.*;
import javax.sql.DataSource;

import srs.bean.Student;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Lookup {

  public static Collection<Student> Students(Connection conn) throws SQLException {
    ResultSet studentResults = getResults(conn, "STUDENT");
    List<Student> students = new ArrayList<Student>();
    while (studentResults.next()){
      Student student = getStudentRow(studentResults);
      students.add(student);
    }
    return students;
  }

  private static Student getStudentRow(ResultSet rs) throws SQLException {
    Student student = new Student();
    student.setfirst_name(rs.getString("FIRST_NAME"));
    student.setLast_name(rs.getString("LAST_NAME"));
    student.setSsn(rs.getString("SSN"));
    student.setEmail(rs.getString("EMAIL"));
    student.setAddress(rs.getString("ADDRESS"));
    student.setUserID(rs.getString("USERID"));
    student.setPassword(rs.getString("FIRST_NAME"));
    return student;
  }

  private static ResultSet getResults(Connection conn, String tableName) throws SQLException {
    String query = "select * from " + tableName;
    Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    return stmt.executeQuery(query);
  }
}