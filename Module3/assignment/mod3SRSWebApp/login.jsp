<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>Guru Registration Form</title>
</head>

<body>
  <form action="jhu/register" method="POST">
    <h1>Welcome to the Student Registration Site</h1>
    <h2>If you already have an account, please log in</h2>
    <table>
      <tr>
        <td>User Id:</td>
        <td><input type="text" name="user_id" /></td>
      </tr>
      <tr>
        <td>Password:</td>
        <td><input type="text" name="password" /></td>
      </tr>
      <tr>
        <td class="field"><input type="submit" name="submit" value="Login" /></td>
        <td class="field"><input type="reset" name="reset" value="Reset" /></td>
      </tr>
    </table>
    <br>
    <br>
    <h2>For new users, please register first</h2>
    <table>
      <tr>
        <td class="field"><input type="submit" name="register" value="Register" /></td>
      </tr>
    </table>
  </form>
</body>

</html>