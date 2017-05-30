package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.RequestHandler;
import domain.Person;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thomas on 17/04/2017.
 */
public class asyncChangeStatus extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        HttpSession session = request.getSession();
        Person requestee = (Person) session.getAttribute("user");
        String newStatus = request.getParameter("status");
        requestee.setStatus(newStatus);
        getService().updatePersons(requestee);

        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(requestee);
        response.setContentType("application/json");
        System.out.println(json);
        response.getWriter().write(json);
        return "async";
    }
}
