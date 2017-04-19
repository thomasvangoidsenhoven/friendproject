<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 12/04/2017
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Friend App | Overview" />
    </jsp:include>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="title" value="overview"/>
</jsp:include>

<div class="container afterNav">

    <h1>Your Friends:</h1>
    <table class="table table-responsive table-striped table-hover">
        <thead>
            <tr>
                <th>email</th>
                <th>firstname</th>
                <th>lastname</th>
                <th>role</th>
            </tr>
        </thead>
        <tbody id="friendtable">
        <c:forEach var="friend" items="${friends}">
            <tr>
                <td>${friend.userId}</td>
                <td>${friend.firstName}</td>
                <td>${friend.lastName}</td>
                <td>${friend.role}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br><br><br><br><br>
    <h1>Person Overview</h1>
    <table class="table table-responsive table-striped table-hover">
        <thead>
            <tr>
                <th>email</th>
                <th>firstname</th>
                <th>lastname</th>
                <th>role</th>
                <th>add friend</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="person" items="${users}">
            <tr>
                <td>${person.userId}</td>
                <td>${person.firstName}</td>
                <td>${person.lastName}</td>
                <td>${person.role}</td>
                <td><a class="addFriend" href="#" id="${person.userId}"  >add friend</a></td>
            </tr>
        </c:forEach>
        </tbody>

    </table>
</div>
<script src="js/overview.js"></script>
</body>
</html>
