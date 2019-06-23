<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SRS Registration</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>

<body>
  <form action="register" method="POST">
    <h1>Registration Form A</h1>
    <table>
      <tr>
        <td>User Id:</td>
        <td><input type="text" name="user_id" /></td>
        <td>
            <% if(session.getAttribute("user_id_error") != null) out.println((String) session.getAttribute("user_id_error"));%>
        </td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="text" name="password" /></td>
        <td>
            <% if(session.getAttribute("password_error") != null) out.println((String) session.getAttribute("password_error"));%>
        </td>
      </tr>
      <tr>
        <td>Password (repeat):</td>
        <td><input type="text" name="password_repeat" /></td>
        <td>
            <% if(session.getAttribute("password_repeat_error") != null) out.println((String) session.getAttribute("password_repeat_error"));%>
        </td>
      </tr>
      <tr>
        <td>First name:</td>
        <td><input type="text" name="first_name" /></td>
        <td>
            <% if(session.getAttribute("first_name_error")!= null) out.println((String) session.getAttribute("first_name_error"));%>
        </td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><input type="text" name="last_name" /></td>
        <td>
            <% if(session.getAttribute("last_name_error") != null) out.println((String) session.getAttribute("last_name_error"));%>
        </td>
      </tr>
      <tr>
        <td>Social Security Number:</td>
        <td><input type="text" name="ssn" /></td>
        <td>
            <% if(session.getAttribute("ssn_error") != null) out.println((String) session.getAttribute("ssn_error"));%>
        </td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" /></td>
        <td>
            <% if(session.getAttribute("email_error") != null) out.println((String) session.getAttribute("email_error"));%>
        </td>
      </tr>
      <tr>
        <td class="field"><input type="submit" name="continueRegistration" value="Continue" /></td>
      </tr>
    </table>
    <br>
    <br>
  </form>
  <c:if test="${requestScope.isValid}">
  <form action="register" method="POST" >
    <h1>Registration Form B</h1>
    <table>
      <tr>
        <td>Address:</td>
        <td><input type="text" name="street_address" /></td>
      </tr>
      <tr>
        <td>City, State:</td>
        <td><input type="text" name="city_state" /></td>
      </tr>
      <tr>
        <td>Zip Code:</td>
        <td><input type="text" name="zip_code" /></td>
      </tr>
      <tr>
        <td class="field"><input type="submit" name="submit" value="Register" /></td>
      </tr>
    </table>
  </form>
</c:if>
</body>

</html>