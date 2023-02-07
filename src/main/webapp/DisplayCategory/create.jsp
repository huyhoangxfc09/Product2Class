
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create new product</title>
</head>
<style>
    .message{
        color:green;
    }
</style>
<body>
<h1>CREATE NEW PRODUCT</h1>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <fieldset style="width: 20%">
        <legend>Category Information</legend>
        <table>
            <tr>
                <td>Id</td>
                <td><input type="text" name="id"></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit">Create</button>
                    <a href="/CategoryServlet"><button type="button">Back to list category</button></a>
                </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
