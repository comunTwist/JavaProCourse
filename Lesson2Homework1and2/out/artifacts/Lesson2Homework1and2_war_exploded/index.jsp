
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <title>Dr. White</title>
</head>
<body>
<% String html = (String)session.getAttribute("html"); %>
<% if (html != null) { %>
<%= html %>
<% } %>
<form action="/data" method="POST">
  Name: <input type="text" name="name"><br>
  Surname: <input type="text" name="surname"><br>
  <br>
  Who are you?<br>
  <input name="sex" type="radio" value="m"> man<br>
  <input name="sex" type="radio" value="w"> woman<br>
  <br>
  Are you 18 years old?<br>
  <input name="age" type="radio" value="y"> yes<br>
  <input name="age" type="radio" value="n"> no<br>
  <input type="submit" />
</form>
</body>
</html>
