<%@ page import="com.icbc.sd.oa3.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>修改部门</title>
</head>
<body>
<%
    Dept dept = (Dept) request.getAttribute("dept");
%>
<form action='<%=request.getContextPath()%>/dept/doEdit' method='post'>
    <label>部门编号：
        <input type='text' name='dept_no' value="<%=dept.getDeptNo()%>"/>
    </label><br/>
    <label>部门名称：
        <input type='text' name='dname' value="<%=dept.getDname()%>"/>
    </label><br/>
    <label>
        部门位置：
        <input type='text' name='location' value="<%=dept.getLocation()%>"/>
    </label><br/>
    <input type='submit' value='修改'/>
<!--    返回上一个页面-->
    <input type='button' value='返回' onclick='history.back()'>
</form>
</body>
</html>