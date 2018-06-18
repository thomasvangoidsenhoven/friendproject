package controller;

import com.google.gson.Gson;
import controller.RequestHandler;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghost on 29/05/2017.
 */
public class asyncPolling extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Person requestee = (Person) session.getAttribute("user");

        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        Gson gson = new Gson();
        if(requestee == null)
        {
            String smallJson = gson.toJson(getService().getPersons());
            response.getWriter().write(smallJson);
            return "async";
        }

        List<List> megaList = new ArrayList<>();
        megaList.add(getService().getPersons());


        megaList.add(getService().getFriends(requestee));



        String json = gson.toJson(megaList);

        response.getWriter().write(json);
        return "async";
    }
}
