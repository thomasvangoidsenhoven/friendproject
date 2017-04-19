<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Friend App | Log In Page" />
</jsp:include>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="title" value="login" />
</jsp:include>
<main class="container afterNav">
    <form action="Controller?action=LogIn" method="POST">
        <div class="form-group">
            <label for="email">Email address</label>
            <input name="email" type="email" class="form-control" id="email" placeholder="Email">
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