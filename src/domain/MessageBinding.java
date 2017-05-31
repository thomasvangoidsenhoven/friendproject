package domain;

import java.util.ArrayList;

/**
 * Created by ghost on 30/05/2017.
 */
public class MessageBinding {
    public static int counter = 0;
    private ArrayList<Message> messages = new ArrayList<>();
    private int id;
    private Person user1;
    private Person user2;


    public MessageBinding()
    {
        id = counter;
        counter++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public Person getUser1() {
        return user1;
    }

    public void setUser1(Person user1) {
        this.user1 = user1;
    }

    public Person getUser2() {
        return user2;
    }

    public void setUser2(Person user2) {
        this.user2 = user2;
    }
}
