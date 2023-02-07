
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
        <legend>Product information</legend>
        <table>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name" value="${requestScope["transmitted"].getName()}"></td>
            </tr>
            <tr>
                <th>Price</th>
                <td><input type="number" name="price" value="${requestScope["transmitted"].getPrice()}"></td>
            </tr>
            <tr>
                <th>Category:</th>
                <td> ${transmitted.category.name}
                    <select name="category">
                        <c:forEach items="${category}" var="element">
                            <option><c:out value="${element.name}"/></option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <th>Quantity</th>
                <td><input type="text" name="quantity" value="${requestScope["transmitted"].getQuantity()}"></td>
            </tr>
            <tr>
                <td><button type="submit">Update</button></td>
                <td><a href="/ProductServlet"><button type="button">Back to home</button> </a> </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
