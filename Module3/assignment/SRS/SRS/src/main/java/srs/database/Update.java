package srs.database;

import srs.bean.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {

  public static void course(Connection conn, Course course) throws SQLException {
    String query = "UPDATE COURSES " 
                 + "SET COURSE_NAME = '" + course.getCourse_name() + "'"
                 + "WHERE COURSEID = " + course.getCourseId() + ";";
    Execute.update(conn, query);
  }

  public static void registrar(Connection conn, Registrar registrar) throws SQLException {
    String query = "UPDATE REGISTRAR " 
                 + "SET NUMBER_STUDENTS_REGISTERED = " + registrar.getNumber_students_registered() + " "
                 + "WHERE COURSEID = " + registrar.getCourseId();
                 System.out.println(query);
    Execute.update(conn, query);
  }

  public static void student(Connection conn, Student student) throws SQLException {
    String query = "UPDATE STUDENT " 
                 + "SET FIRST_NAME = '" + student.getfirst_name() + "', "
                 + "LAST_NAME = '" + student.getLast_name() + "', "
                 + "SSN = '" + student.getSsn() + "', "
                 + "EMAIL = '" + student.getEmail() + "', "
                 + "ADDRESS = '" + student.getAddress() + "', "
                 + "PASSWORD = '" + student.getPassword() + "', "
                 + "WHERE USERID = '" + student.getUserID() + "';";
     Execute.update(conn, query);
  }
}