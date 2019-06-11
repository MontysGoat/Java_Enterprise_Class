package mod1;

import java.util.Objects;

public class Student 
{
  String firstName = null;
  String lastName = null;
  String ssn = null;
  String email = null;
  String address = null;
  String userID = null;
  String password = null;


  public Student() {
  }

  public Student(String firstName, String lastName, String ssn, String email, String address, String userID, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.ssn = ssn;
    this.email = email;
    this.address = address;
    this.userID = userID;
    this.password = password;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public Student firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public Student lastName(String lastName) {
    this.lastName = lastName;
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
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(ssn, student.ssn) && Objects.equals(email, student.email) && Objects.equals(address, student.address) && Objects.equals(userID, student.userID) && Objects.equals(password, student.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, ssn, email, address, userID, password);
  }

  @Override
  public String toString() {
    return "{" +
      " firstName='" + getFirstName() + "'" +
      ", lastName='" + getLastName() + "'" +
      ", ssn='" + getSsn() + "'" +
      ", email='" + getEmail() + "'" +
      ", address='" + getAddress() + "'" +
      ", userID='" + getUserID() + "'" +
      ", password='" + getPassword() + "'" +
      "}";
  }
  
}