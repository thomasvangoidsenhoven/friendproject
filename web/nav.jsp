<%--
  Created by IntelliJ IDEA.
  User: Thomas
  Date: 12/04/2017
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-inverse  navbar-fixed-top">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Person Manager</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">

                <c:choose>
                    <c:when test="${param.title=='home'}">
                        <li  class="active"><a href="#">Home</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="Controller">Home</a></li>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${param.title=='overview'}">
                        <li  class="active"><a href="#">Overview</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="Controller?action=Overview">Overview</a></li>
                    </c:otherwise>
                </c:choose>



            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Account <span class="caret"></span></a>
                    <ul class="dropdown-menu">

                        <c:choose>
                            <c:when test="${user!=null}">
                                <li><a href="#">Account settings</a></li>
                                <li><a href="Controller?action=LogOut">Logout</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="Controller?action=LogInForm">Log In</a></li>
                                <li><a href="Controller?action=AccountForm">Register</a></li>
                            </c:otherwise>
                        </c:choose>



                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
