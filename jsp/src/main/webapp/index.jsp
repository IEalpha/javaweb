<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World! 你好" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet 你好</a>
<%
  request.setAttribute("name", "JSP");
%>
${name}
</body>
</html>