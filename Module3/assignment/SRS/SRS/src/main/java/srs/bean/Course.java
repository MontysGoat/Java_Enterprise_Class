package srs.bean;

import java.io.Serializable;
import java.util.Objects;

public class Course implements Serializable {
  private int courseId;
  private String course_name;

  public Course() {
  }

  public Course(int courseId, String course_name) {
    this.courseId = courseId;
    this.course_name = course_name;
  }

  public int getCourseId() {
    return this.courseId;
  }

  public void setCourseId(int courseId) {
    this.courseId = courseId;
  }

  public String getCourse_name() {
    return this.course_name;
  }

  public String getFull_name(){
    return getCourseId() + " - " + getCourse_name();
  }

  public void setCourse_name(String course_name) {
    this.course_name = course_name;
  }

  public Course courseId(int courseId) {
    this.courseId = courseId;
    return this;
  }

  public Course course_name(String course_name) {
    this.course_name = course_name;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return courseId == course.courseId && Objects.equals(course_name, course.course_name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(courseId, course_name);
  }

  @Override
  public String toString() {
    return "{" +
      " courseId='" + getCourseId() + "'" +
      ", course_name='" + getCourse_name() + "'" +
      "}";
  }
}