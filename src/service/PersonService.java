package service;

import db.PersonRepository;
import domain.Person;

import java.util.List;

/**
 * Created by Thomas on 12/04/2017.
 */
public class PersonService
{

    private PersonRepository rep = new PersonRepository();
    public Person getPerson(String personId)  {
        return getPersonRepository().get(personId);
    }

    public List<Person> getPersons() {
        return getPersonRepository().getAll();
    }

    public List<Person> getFriends(Person person)
    {
        return getPersonRepository().getFriends(person);
    }

    public void addFriend(String userId, String friendId)
    {
        getPersonRepository().addFriend(userId,friendId);
    }

    public void addPerson(Person person) {
        getPersonRepository().add(person);
    }

    public void updatePersons(Person person) {
        getPersonRepository().update(person);
    }

    public void deletePerson(String id) {
        getPersonRepository().delete(id);
    }

    public Person getAuthenticatedUser(String email, String password) {
        return getPersonRepository().getAuthenticatedUser(email, password);
    }

    private PersonRepository getPersonRepository() {
        return rep;
    }


}
