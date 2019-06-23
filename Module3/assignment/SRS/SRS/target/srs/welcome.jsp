<%@ page language="java" contentType="text/html; charset=ISO-8859-1"pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>SRS Welcome</title>
</head>

<body>
  <h1>Welcome     
      <%! String first_name = (String) session.getAttribute("first_name");%>
      <% String last_name = (String) session.getAttribute("last_name");%>
      <% out.println(firstName + " " + lastName);%>
    !</h1>
</body>

</html>