package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Thomas on 13/04/2017.
 */
public class Overview extends RequestHandler
{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Person currentUser = (Person) session.getAttribute("user");
        request.setAttribute("friends", super.getService().getFriends(currentUser));
        request.setAttribute("users",super.getService().getPersons());
        return "overview.jsp";
    }
}
