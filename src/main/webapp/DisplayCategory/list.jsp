<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Product</title>
</head>
<style>
    table,td,th{
        border: black solid 1px;
    }
    table{
        border-collapse: collapse;
        text-align: center;
    }
</style>
<body>
<h1>LIST CATEGORY</h1>
<form>
    <a href="/CategoryServlet?action=create"><button type="button">Create new category</button></a>
</form>
<form>
    <a href="/ProductServlet"><button type="button">Back home</button></a>
</form>
<table>
    <tr>
        <th colspan="4">LIST CATEGORY</th>
    </tr>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>ACTION</th>
    </tr>
    <c:forEach var="element" items='${requestScope["transmitted"]}'>
        <tr>
            <td>${element.getId()}</td>
            <td>${element.getName()}</td>
            <td colspan="2">
                <a href="/CategoryServlet?action=update&id=${element.getId()}"><button type="button>">Update</button> </a>
                <a href="/CategoryServlet?action=delete&id=${element.getId()}"><button type="button">Delete</button> </a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
