<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎</title>
</head>
<body>
<h1><%=session.getAttribute("username")%>，欢迎来到办公系统</h1>
<hr/>
<a href="<%=request.getContextPath()%>/dept/list">部门列表查看</a>
<hr/>
<button onclick="location.href='<%=request.getContextPath()%>/user/logout'">退出登录</button>

</body>
</html>