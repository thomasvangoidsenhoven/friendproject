package controller;

import com.google.gson.Gson;
import domain.Person;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Thomas on 17/04/2017.
 */
public class asyncChangeStatus extends RequestHandler  {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        HttpSession session = request.getSession();
        Person requestee = (Person) session.getAttribute("user");
        Gson gson = new Gson();
        String json = gson.toJson(requestee);

        System.out.println(json);
        response.getWriter().write(json);
        return "async";
    }
}
