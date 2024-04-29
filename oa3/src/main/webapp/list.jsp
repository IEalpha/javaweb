<%@ page import="java.util.List" %>
<%@ page import="com.icbc.sd.oa3.bean.Dept" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='UTF-8'>
    <title>部门信息</title>
</head>
<body>
<h1>部门信息</h1>
<hr/>
<table border='1' width='50%' align='center'>
    <tr>
        <th>序号</th>
        <th>部门编号</th>
        <th>部门名称</th>
        <th>操作</th>
    </tr>
    <%
        List<Dept> deptList = (List<Dept>) request.getAttribute("deptList");
        if (deptList != null && deptList.size() > 0) {
            for (int i = 0; i < deptList.size(); i++) {
    %>
    <tr>
        <td><%=i + 1%></td>
        <td><%=deptList.get(i).getDeptNo()%></td>
        <td><%=deptList.get(i).getDname()%></td>
        <td>
            <a href='<%=request.getContextPath()%>/dept/detail?dept_no=<%=deptList.get(i).getDeptNo()%>'>查看</a>
            <a href='<%=request.getContextPath()%>/dept/edit?dept_no=<%=deptList.get(i).getDeptNo()%>'>编辑</a>
            <a href='#' onclick='deleteDept(<%=deptList.get(i).getDeptNo()%>)'>删除</a>
        </td>
    </tr>

    <%
            }
        }
    %>

</table>
<button><a href='<%=request.getContextPath()%>/add.jsp'>添加部门</a></button>
</body>
<script type='text/javascript'>
    function deleteDept(dept_no) {
        if (confirm('确定要删除' + dept_no + '部门吗？')) {
            window.location.href = '<%=request.getContextPath()%>/dept/delete?dept_no=' + dept_no;
        }
    }
</script>
</html>