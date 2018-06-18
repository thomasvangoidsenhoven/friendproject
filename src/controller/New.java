package controller;


import domain.Person;
import domain.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class New extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Person currentUser = (Person) session.getAttribute("user");
        if(currentUser == null) return "index.jsp";

        return "new.jsp";
    }
}
