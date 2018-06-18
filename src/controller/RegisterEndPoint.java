package controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterEndPoint extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jObj = (JsonObject) parser.parse(request.getReader());


        Person acc = new Person();
        String userId = jObj.get("userId").toString();
        String password = jObj.get("password").toString();
        String firstName = jObj.get("firstName").toString();
        String lastName = jObj.get("lastName").toString();


        // removes ""
        userId = userId.substring(1,userId.length()-1);
        password = password.substring(1,password.length()-1);
        firstName = firstName.substring(1,firstName.length()-1);
        lastName = lastName.substring(1,lastName.length()-1);

        acc.setUserId(userId);
        acc.setHashedPassword(password);
        acc.setFirstName(firstName);
        acc.setLastName(lastName);

        acc.setStatus("online");


        this.getService().addPerson(acc);
        System.out.println(acc.getUserId()+" just registered");
        Gson gson = new Gson();
        response.getWriter().write(gson.toJson(acc));
        return "async";
    }
}
