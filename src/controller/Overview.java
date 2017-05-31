package controller;

import domain.Person;
import domain.Status;

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
        if(currentUser == null) return "index.jsp";
        request.setAttribute("currentStatus", currentUser.getStatus());
        request.setAttribute("statuss", Status.values());
        request.setAttribute("friends", super.getService().getFriends(currentUser));
        request.setAttribute("users",super.getService().getPersons());
        return "overview.jsp";
    }
}
