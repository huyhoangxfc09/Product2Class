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
<h1>LIST PRODUCT</h1>
<form>
  <a href="/ProductServlet?action=create"><button type="button">Create new product</button></a>
</form>
<form>
  <a href="/CategoryServlet"><button type="button">Category</button></a>
</form>
<table>
  <tr>
    <th colspan="7">LIST PRODUCT</th>
  </tr>
  <tr>
    <th>ID</th>
    <th>NAME</th>
    <th>PRICE</th>
    <th>CATEGORY</th>
    <th>QUANTITY</th>
    <th>ACTION</th>
  </tr>
  <c:forEach var="element" items='${requestScope["transmitted"]}'>
    <tr>
      <td>${element.getId()}</td>
      <td>${element.getName()}</td>
      <td>${element.getPrice()}</td>
      <td>${element.getCategory().getName()}</td>
      <td>${element.getQuantity()}</td>
      <td colspan="2">
        <a href="/ProductServlet?action=update&id=${element.getId()}"><button type="button>">Update</button> </a>
        <a href="/ProductServlet?action=delete&id=${element.getId()}"><button type="button">Delete</button> </a>
      </td>
    </tr>
  </c:forEach>
</table>
</body>
</html>
