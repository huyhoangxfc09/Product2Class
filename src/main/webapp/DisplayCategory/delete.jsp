
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<body>
<h1>DELETE PRODUCT</h1>
<form method="post">
    <fieldset style="width: 20%">
        <legend>Are you sure?</legend>
        <table>
            <tr>
                <th>Id</th>
                <td>${requestScope["transmitted"].getId()}</td>
            </tr>
            <tr>
                <th>Name</th>
                <td>${requestScope["transmitted"].getName()}</td>
            </tr>
            <tr>
                <td><a href="/ProductServlet"><button type="button">Back to list category</button></a></td>
                <td><button type="submit">Delete</button> </td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
