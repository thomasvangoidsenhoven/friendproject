package controller;

import domain.Person;
import domain.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Thomas on 14/04/2017.
 */
public class LogOut extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession();
        Person person = (Person) session.getAttribute("user");
        person.setStatus(Status.OFFLINE);
        session.invalidate();
        return "index.jsp";
    }
}
