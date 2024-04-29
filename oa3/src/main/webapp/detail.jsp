<%@ page import="com.icbc.sd.oa3.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>查看部门</title>
</head>
<body>
<%
    Dept dept = (Dept) request.getAttribute("dept");
%>
    <label>部门编号：
        <input type='text' name='dept_no' value="<%=dept.getDeptNo()%>" readonly/>
    </label><br/>
    <label>部门名称：
        <input type='text' name='dname' value="<%=dept.getDname()%>" readonly/>
    </label><br/>
    <label>
        部门位置：
        <input type='text' name='location' value="<%=dept.getLocation()%>" readonly/>
    </label><br/>
    <!--    返回上一个页面-->
    <input type='button' value='返回' onclick='history.back()'>
</body>
</html>