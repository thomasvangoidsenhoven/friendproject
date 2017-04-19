<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 13/04/2017
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Friend App | Register Page" />
</jsp:include>
<body>
<jsp:include page="nav.jsp">
    <jsp:param name="title" value="register" />
</jsp:include>
<main class="container afterNav">
    <form action="Controller?action=AccountFormSubmit" method="POST">
        <div class="form-group">
            <label for="id">Email address</label>
            <input name="id" type="email" class="form-control" id="id" placeholder="Email">
        </div>


        <div class="form-group">
            <label for="firstname">firstname</label>
            <input type="text" class="form-control" id="firstname" name="firstname">
        </div>

        <div class="form-group">
            <label for="firstname">lastname</label>
            <input type="text" class="form-control" id="lastname" name="lastname">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</main>
</body>
</html>
