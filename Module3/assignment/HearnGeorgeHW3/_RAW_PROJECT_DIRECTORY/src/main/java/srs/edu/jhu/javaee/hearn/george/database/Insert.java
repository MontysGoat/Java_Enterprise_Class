package srs.edu.jhu.javaee.hearn.george.database;

import srs.edu.jhu.javaee.hearn.george.bean.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {

  public static void course(Connection conn, Course course) throws SQLException {
    String query = "insert into COURSES VALUES ("
                                        + course.getCourseId()+", '"
                                        + course.getCourse_name() + "')";
    Execute.update(conn, query);
  }

  public static void registrar(Connection conn, Registrar registrar) throws SQLException {
    String query = "insert into REGISTRAR VALUES ("
                                        + registrar.getCourseId()+", '"
                                        + registrar.getNumber_students_registered() + "')";
    Execute.update(conn, query);
  }

  public static void student(Connection conn, Student student) throws SQLException {
    String query = "insert into STUDENT VALUES ('"
                                        + student.getfirst_name() + "','" 
                                        + student.getLast_name() +  "','" 
                                        + student.getSsn() +       "','"
                                        + student.getEmail() +     "','" 
                                        + student.getAddress() +   "','" 
                                        + student.getUserID() +    "','" 
                                        + student.getPassword() +  "')";
    Execute.update(conn, query);
  }
}