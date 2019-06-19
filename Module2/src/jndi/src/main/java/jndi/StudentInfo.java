package jndi;

import java.io.Serializable;
import java.util.Objects;

public class StudentInfo implements Serializable
{
  private static final long serialVersionUID = 1L;
  String first_name = null;
  String last_name = null;
  String phone = null;
  String email = null;
  String address = null;


  public StudentInfo() {
  }

  public StudentInfo(String first_name, String last_name, String phone, String email, String address) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone = phone;
    this.email = email;
    this.address = address;
  }

  public String getfirst_name() {
    return this.first_name;
  }

  public void setfirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getlast_name() {
    return this.last_name;
  }

  public void setlast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getPone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
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


  public StudentInfo first_name(String first_name) {
    this.first_name = first_name;
    return this;
  }

  public StudentInfo last_name(String last_name) {
    this.last_name = last_name;
    return this;
  }

  public StudentInfo phone(String phone) {
    this.phone = phone;
    return this;
  }

  public StudentInfo email(String email) {
    this.email = email;
    return this;
  }

  public StudentInfo address(String address) {
    this.address = address;
    return this;
  }

  @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StudentInfo)) {
            return false;
        }
        StudentInfo student = (StudentInfo) o;
        return Objects.equals(first_name, student.first_name) && Objects.equals(last_name, student.last_name) && Objects.equals(phone, student.phone) && Objects.equals(email, student.email) && Objects.equals(address, student.address);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name, phone, email, address);
  }

  @Override
  public String toString() {
    return "{" +
      " first_name='" + getfirst_name() + "'" +
      ", last_name='" + getlast_name() + "'" +
      ", phone='" + getPone() + "'" +
      ", email='" + getEmail() + "'" +
      ", address='" + getAddress() + "'" +
      "}";
  }
  
}