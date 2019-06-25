<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SRS Welcome</title>
</head>

<body>
  <h1>Welcome
    <% String first_name = (String) request.getSession().getAttribute("first_name"); %>
    <% String last_name = (String) request.getSession().getAttribute("last_name");%>
    <% out.println(first_name + " " + last_name);%>
    !</h1>
  <h2>Please choose your next action:</h2>

  <form action="register" method="POST">
    <table>
      <tr>
        <td>
          <input type="radio" id="class_register" name="welcome" value="class_register" checked>
          <label for="class_register">Register for classes</label>
        </td>
      </tr>
      <tr>
        <td>
          <input type="radio" id="logout" name="welcome" value="logout">
          <label for="logout">Logout</label>
        </td>
      </tr>
      <tr>
        <td class="field"><input type="submit" name="submit" value="Submit" /></td>
      </tr>
    </table>
  </form>
</body>

</html>