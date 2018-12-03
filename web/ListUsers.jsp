<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Danilius
  Date: 03.12.2018
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список пользователей</title>
    <link rel="stylesheet" type="text/css" href="WEB-INF/css/main.css"/>
</head>
<body>
<h1>Список пользователей:</h1>
<table class="table" align="center">
    <thead>
    <td>Имя</td>
    <td>Права</td>
    <td>Почта</td>
    </thead>
    <tbody>
   <%-- <c:forEach items="${users}" var="user">
    <tr>
        <td>${user.name}
        </td>
        <td>${user.permission}
        </td>
        <td>${user.email}
        </td>
        <td><a href="/editUser?user_id=${user.user_id}">Изменить</a></td>
        <td><a href="/deleteUser?user_id=${user.user_id}">Удалить</a></td>
    </tr>--%>
    </tbody>
    <%--</c:forEach>--%>
</table>
</body>
</html>
