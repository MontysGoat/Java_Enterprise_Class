package srs.edu.jhu.javaee.hearn.george.bean;

import java.io.Serializable;
import java.util.Objects;

public class Registrar implements Serializable {
  private int courseId;
  private int number_students_registered;

  public Registrar() {
  }

  public Registrar(int courseId, int number_students_registered) {
    this.courseId = courseId;
    this.number_students_registered = number_students_registered;
  }

  public int getCourseId() {
    return this.courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public int getNumber_students_registered() {
    return this.number_students_registered;
  }

  public void setNumber_students_registered(int number_students_registered) {
    this.number_students_registered = number_students_registered;
  }

  public Registrar courseId(int courseId) {
    this.courseId = courseId;
    return this;
  }

  public Registrar number_students_registered(int number_students_registered) {
    this.number_students_registered = number_students_registered;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Registrar)) {
            return false;
        }
        Registrar registrar = (Registrar) o;
        return courseId == registrar.courseId && number_students_registered == registrar.number_students_registered;
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, number_students_registered);
  }

  @Override
  public String toString() {
    return "{" +
      " courseId='" + getCourseId() + "'" +
      ", number_students_registered='" + getNumber_students_registered() + "'" +
      "}";
  }
}