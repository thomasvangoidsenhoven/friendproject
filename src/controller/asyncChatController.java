package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Message;
import domain.MessageBinding;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by thomas on 30/05/2017.
 */
public class asyncChatController extends RequestHandler{
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request");
        HttpSession session = request.getSession();
        Person requestee = (Person) session.getAttribute("user");
        String strategy = request.getParameter("strategy");
        System.out.println("strategy: " +strategy);
        System.out.println(strategy);
        String json = "";
        Gson gson = new GsonBuilder().create();
        if (strategy.equals("createRoom"))
        {
            String friendId  = request.getParameter("friendId");

            MessageBinding messageBinding = new MessageBinding();
            messageBinding.setUser1(requestee);
            messageBinding.setUser2(getService().getPerson(friendId));

            getService().getPerson(friendId).getMessageBindings().put(messageBinding.getId(),messageBinding);
            getService().getPerson(requestee.getUserId()).getMessageBindings().put(messageBinding.getId(),messageBinding);
            List<String> users = new ArrayList<>();
            users.add(getService().getPerson(friendId).getFirstName());
            users.add(requestee.getFirstName());
            users.add(Integer.toString(messageBinding.getId()));
            json = gson.toJson(users);


        }else if(strategy.equals("getRooms"))
        {
            List<MessageBinding> list = new ArrayList<>(getService().getPerson(requestee.getUserId()).getMessageBindings().values());
            json = gson.toJson(list);
            System.out.println(json);
        }
        else if(strategy.equals("postMessage"))
        {
            String bindingId = request.getParameter("bindingId");
            Integer i = Integer.parseInt(bindingId);
            String message = request.getParameter("message");
            System.out.println(bindingId);
            System.out.println(message);
            MessageBinding binding = getService().getPerson(requestee.getUserId()).getMessageBindings().get(i);
            Message nMessage = new Message();
            nMessage.setMessage(message);
            nMessage.setAuthor(requestee.getFirstName());

            binding.getMessages().add(nMessage);
            json = gson.toJson(nMessage);

        }
        response.setContentType("application/json");
        response.getWriter().write(json);
        return "async";
    }
}
