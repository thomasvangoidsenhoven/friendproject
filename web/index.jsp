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
                <input id="input1s" type="number">
            </article>
            <article>
                <h3>Wat vind jij van het dit geweldig vak?</h3>
                <ul id="k2"></ul>
                <input id="input2" type="text"> <button id="2" class="submit" >Submit</button>
                <input id="input2s" type="number">

            </article>
            <article>
                <h3>Hoe is het weer bij jou?</h3>
                <ul id="k3"></ul>
                <input id="input3" type="text"> <button id="3" class="submit" >Submit</button>
                <input id="input3s" type="number">
            </article>
            <article>
                <h3>Wat vind jij van de nieuwe import tax?</h3>
                <ul id="k4"></ul>
                <input id="input4" type="text"> <button id="4" class="submit" >Submit</button>
                <input id="input4s" type="number">
            </article>
            <article>
                <h3>Gaat de brexit zelfs nog doorgaan?</h3>
                <ul id="k5"></ul>
                <input id="input5" type="text"><button id="5" class="submit" >Submit</button>
                <input id="input5s" type="number" >

            </article>
        </section>
</main>
<script src="js/index.js"></script>
</body>
</html>