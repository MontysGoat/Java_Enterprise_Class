package srs.database;

import javax.naming.*;
import javax.sql.DataSource;

import srs.bean.Course;
import srs.bean.Registrar;
import srs.bean.Student;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Lookup {

  public static Collection<Student> students(Connection conn) throws SQLException {
    ResultSet studentResults = getResults(conn, "STUDENT");
    List<Student> students = new ArrayList<Student>();
    while (studentResults.next()){
      Student student = getStudentRow(studentResults);
      students.add(student);
    }
    return students;
  }

  public static Collection<Course> courses(Connection conn) throws SQLException {
    ResultSet courseResults = getResults(conn, "COURSES");
    List<Course> courses = new ArrayList<Course>();
    while (courseResults.next()){
      Course course = getCourseRow(courseResults);
      courses.add(course);
    }
    return courses;
  }

  public static Collection<Registrar> registrars(Connection conn) throws SQLException {
    ResultSet registrarResults = getResults(conn, "REGISTRSR");
    List<Registrar> registrars = new ArrayList<Registrar>();
    while (registrarResults.next()){
      Registrar registrar = getRegistrarRow(registrarResults);
      registrars.add(registrar);
    }
    return registrars;
  }
  
  private static Registrar getRegistrarRow(ResultSet rs) throws SQLException {
    Registrar registrar = new Registrar();
    registrar.setCourseId(rs.getInt("COURSEID"));
    registrar.setNumber_students_registered(rs.getInt("NUMBER_STUDENTS_REGISTERED"));
    return registrar;
  }

  private static Course getCourseRow(ResultSet rs) throws SQLException {
    Course course = new Course();
    course.setCourseId(rs.getInt("COURSEID"));
    course.setCourse_name(rs.getString("COURSE_NAME"));
    return course;
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