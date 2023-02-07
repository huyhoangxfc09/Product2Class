
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update product </title>
</head>
<style>
    .message{
        color:green;
    }
</style>
<body>
<h1>UPDATE PRODUCT</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset style="width: 20%">
        <legend>Category information</legend>
        <table>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name" value="${requestScope["transmitted"].getName()}"></td>
            </tr>
            <tr>
                <td><button type="submit">Update</button></td>
                <td><a href="/CategoryServlet"><button type="button">Back to list category</button> </a> </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
