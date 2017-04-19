package controller;

import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thomas on 17/04/2017.
 */
public class asyncAddFriend extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response)throws IOException {
        HttpSession session = request.getSession();
        Person requestee = (Person) session.getAttribute("user");
        Person requested = getService().getPerson(request.getParameter("id"));

        getService().addFriend(requestee.getUserId(),requested.getUserId());
        String json =
                "{\n"+
                    "\"friend\" :{\n"+
                        "\"id\" : " + "\"" + requested.getUserId() + "\"" + ",\n"+
                        "\"firstname\" : "  + "\"" + requested.getFirstName() + "\"" + ",\n"+
                        "\"lastname\" : " + "\"" + requested.getLastName() + "\"" + ",\n"+
                        "\"role\" : " + "\"" + requested.getRole() + "\"" + "\n"+
                    "} \n"+
                "}"
        ;
        System.out.println(json);
        response.getWriter().write(json);
        return "async";
    }
}