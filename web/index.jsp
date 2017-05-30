<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="nl">
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Friend App | Home Page" />
</jsp:include>
<body>

<jsp:include page="nav.jsp">
    <jsp:param name="title" value="home" />
</jsp:include>
<main class="container afterNav">

        <h1> Gegroet! </h1>
        <p>Person - Friend app om vriendjes te maken, dingen te versturen en andere crazy stuff!</p>

        <h1>Blog</h1>
    <input type="hidden" id="user" value="${user.userId}">
        <section class="flexbox">
            <article>
                <h3>Naar welke muziek ben je aan het luisteren?</h3>
                <ul id="k1"></ul>
                <input id="input1" type="text"> <button id="1" class="submit">Submit</button>
            </article>
            <article>
                <h3>Wat vind jij van het dit geweldig vak?</h3>
                <ul id="k2"></ul>
                <input id="input2" type="text"> <button id="2" class="submit" >Submit</button>
            </article>
            <article>
                <h3>Hoe is het weer bij jou?</h3>
                <ul id="k3"></ul>
                <input id="input3" type="text"> <button id="3" class="submit" >Submit</button>
            </article>
        </section>
</main>
<script src="js/index.js"></script>
</body>
</html>