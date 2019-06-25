<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SRS Registration</title>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<%

String user_id = (String) request.getSession().getAttribute("user_id");
user_id = (user_id != null) ? user_id : "";
String password = (String) request.getSession().getAttribute("password");
password = (password != null) ? password : "";
String password_repeat = (String) request.getSession().getAttribute("password_repeat");
password_repeat = (password_repeat != null) ? password_repeat : "";
String first_name = (String) request.getSession().getAttribute("first_name");
first_name = (first_name != null) ? first_name : "";
String last_name = (String) request.getSession().getAttribute("last_name");
last_name = (last_name != null) ? last_name : "";
String ssn = (String) request.getSession().getAttribute("ssn");
ssn = (ssn != null) ? ssn : "";
String email = (String) request.getSession().getAttribute("email");
email = (email != null) ? email : "";
%>

<body>
  <form action="register" method="POST">
    <h1>Registration Form A</h1>
    <table>
      <tr>
        <td>User Id:</td>
        <td><input type="text" name="user_id" value='<%=user_id%>' /></td>
        <td>
          <% if(session.getAttribute("user_id_error") != null) out.println((String) session.getAttribute("user_id_error"));%>
        </td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="text" name="password" value='<%= password %>' /></td>
        <td>
          <% if(session.getAttribute("password_error") != null) out.println((String) session.getAttribute("password_error"));%>
        </td>
      </tr>
      <tr>
        <td>Password (repeat):</td>
        <td><input type="text" name="password_repeat" value='<%= password_repeat %>' /></td>
        <td>
          <% if(session.getAttribute("password_repeat_error") != null) out.println((String) session.getAttribute("password_repeat_error"));%>
        </td>
      </tr>
      <tr>
        <td>First name:</td>
        <td><input type="text" name="first_name" value='<%= first_name %>' /></td>
        <td>
          <% if(session.getAttribute("first_name_error")!= null) out.println((String) session.getAttribute("first_name_error"));%>
        </td>
      </tr>
      <tr>
        <td>Last name:</td>
        <td><input type="text" name="last_name" value='<%= last_name %>' /></td>
        <td>
          <% if(session.getAttribute("last_name_error") != null) out.println((String) session.getAttribute("last_name_error"));%>
        </td>
      </tr>
      <tr>
        <td>Social Security Number:</td>
        <td><input type="text" name="ssn" value='<%= ssn %>' /></td>
        <td>
          <% if(session.getAttribute("ssn_error") != null) out.println((String) session.getAttribute("ssn_error"));%>
        </td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" value='<%= email %>' /></td>
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
    <form action="register" method="POST">
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
          <td class="field"><input type="submit" name="finishRegistration" value="Register" /></td>
        </tr>
      </table>
    </form>
  </c:if>
</body>

</html>