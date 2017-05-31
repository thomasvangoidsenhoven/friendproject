package db;

import domain.Person;
import domain.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Thomas on 16/03/2017.
 */
public class PersonRepository
{
    private static HashMap<String,Person> persons = new HashMap<String, Person>();

    public PersonRepository()
    {
        Person person = new Person();
        person.setUserId("admin@admins.com");
        person.setFirstName("admin");
        person.setLastName("admin");
        person.setHashedPassword("t");
        person.setRole(Role.ADMIN);
        person.setStatus("Offline");
        add(person);

        Person member = new Person();
        member.setUserId("thomas@members.com");
        member.setFirstName("thomas");
        member.setHashedPassword("t");
        member.setStatus("Offline");
        add(member);


    }

    public Person get(String personId){
        if(personId == null){
            throw new IllegalArgumentException("No id given");
        }
        return persons.get(personId);
    }

    public List<Person> getAll(){
        return new ArrayList<Person>(persons.values());
    }

    public void add(Person person){
        if(person == null){
            throw new IllegalArgumentException("No person given");
        }
        if (persons.containsKey(person.getUserId())) {
            throw new IllegalArgumentException("User already exists");
        }
        persons.put(person.getUserId(), person);
    }

    public void update(Person person){
        if(person == null){
            throw new IllegalArgumentException("No person given");
        }
        persons.put(person.getUserId(), person);
    }

    public void delete(String personId){
        if(personId == null){
            throw new IllegalArgumentException("No id given");
        }
        persons.remove(personId);
    }

    public Person getAuthenticatedUser(String email, String password) {
        Person person = get(email);

        if (person != null && person.isCorrectPassword(password)) {
            return person;
        }
        else {
            return null;
        }
    }

    public void addFriend(String userId, String friendId)
    {
        get(userId).addFriend(get(friendId));
    }

    public List<Person> getFriends(Person person) {
        return get(person.getUserId()).getAllFriends();
    }
}
