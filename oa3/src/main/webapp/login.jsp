<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: IEalp
  Date: 2024-04-12
  Time: 13:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<%=request.getSession()%>
<%
    String contextPath = request.getContextPath();
    String errCode = request.getParameter("error");
    Cookie[] cookies = request.getCookies();
    Map<String, String> cookieMap = new HashMap<>();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie.getValue());
        }
    }
    if ((errCode == null || errCode.isEmpty()) && cookieMap.containsKey("autoLogin") && cookieMap.get("autoLogin").equals("on")) {
        response.sendRedirect(contextPath + "/login?username=" + cookieMap.get("username") + "&password=" + cookieMap.get("password") + "&autoLogin=" + cookieMap.get("autoLogin"));
    }
%>
<script type="text/javascript">
    var errCode = '<%=errCode%>';
    if (errCode === '1') {
        alert('用户名或密码错误');
    }
</script>
<form action="<%=contextPath%>/login" method="post">
    <label>部门编号：
        <input type='text' name='username'/>
    </label><br/>
    <label>部门名称：
        <input type='password' name='password'/>
    </label><br/>
    <label>
        <input type="checkbox" name="autoLogin"/>十天内免登录
        <input type='submit' value='登录'/>
    </label>
</form>
</body>
</html>
