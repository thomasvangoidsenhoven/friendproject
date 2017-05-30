package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.PersonService;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by thomas on 29/05/2017.
 */


@ServerEndpoint("/blog")
public class EndPoint
{
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());



    @OnOpen
    public void onOpen(Session session)
    {
        System.out.println(session.getId() + " has opened a connection");

        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session)
    {
        System.out.println("Message from " + session.getId() + ": " + message);


        sendMessageToAll(message);
    }

    @OnClose
    public void onClose(Session session)
    {
        System.out.println("Chat " +session.getId()+" has ended");
        sessions.remove(session);
    }

    private void sendMessageToAll(String message)
    {
        for(Session s : sessions)
        {
            try
            {

                s.getBasicRemote().sendText(message);
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
