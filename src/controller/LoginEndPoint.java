package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginEndPoint extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonParser parser = new JsonParser();
        JsonObject jObj = (JsonObject) parser.parse(request.getReader());
        Gson gson = new Gson();
        String email = jObj.get("userId").toString();
        email = email.substring(1,email.length()-1);
        String pass = jObj.get("password").toString();
        pass = pass.substring(1,pass.length()-1);

        Person person = getService().getAuthenticatedUser(email,pass);
        System.out.println(person.getUserId());
        if( person != null)
        {
            response.getWriter().write(gson.toJson(person));
            response.setStatus(200);
        }else { response.setStatus(400); }
        return "async";
    }
}
