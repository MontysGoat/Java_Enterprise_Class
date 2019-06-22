

import java.io.Serializable;
import java.util.Objects;

public class Student implements Serializable
{
  String first_name = null;
  String last_name = null;
  String ssn = null;
  String email = null;
  String address = null;
  String userID = null;
  String password = null;


  public Student() {
  }

  public Student(String first_name, String last_name, String ssn, String email, String address, String userID, String password) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.ssn = ssn;
    this.email = email;
    this.address = address;
    this.userID = userID;
    this.password = password;
  }

  public String getfirst_name() {
    return this.first_name;
  }

  public void setfirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return this.last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getSsn() {
    return this.ssn;
  }

  public void setSsn(String ssn) {
    this.ssn = ssn;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getUserID() {
    return this.userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Student first_name(String first_name) {
    this.first_name = first_name;
    return this;
  }

  public Student last_name(String last_name) {
    this.last_name = last_name;
    return this;
  }

  public Student ssn(String ssn) {
    this.ssn = ssn;
    return this;
  }

  public Student email(String email) {
    this.email = email;
    return this;
  }

  public Student address(String address) {
    this.address = address;
    return this;
  }

  public Student userID(String userID) {
    this.userID = userID;
    return this;
  }

  public Student password(String password) {
    this.password = password;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return Objects.equals(first_name, student.first_name) && Objects.equals(last_name, student.last_name) && Objects.equals(ssn, student.ssn) && Objects.equals(email, student.email) && Objects.equals(address, student.address) && Objects.equals(userID, student.userID) && Objects.equals(password, student.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name, ssn, email, address, userID, password);
  }

  @Override
  public String toString() {
    return "{" +
      " first_name='" + getfirst_name() + "'" +
      ", last_name='" + getLast_name() + "'" +
      ", ssn='" + getSsn() + "'" +
      ", email='" + getEmail() + "'" +
      ", address='" + getAddress() + "'" +
      ", userID='" + getUserID() + "'" +
      ", password='" + getPassword() + "'" +
      "}";
  }
  
}