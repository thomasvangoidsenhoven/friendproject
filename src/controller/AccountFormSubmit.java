package controller;

import domain.DomainException;
import domain.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Thomas on 13/04/2017.
 */
public class AccountFormSubmit extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        String id = request.getParameter("id");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String password = request.getParameter("password");

        Person person = new Person();
        try {
            person.setUserId(id);
        }catch (DomainException e)
        {
            errors.add(e.getMessage());
        }
        try {
            person.setHashedPassword(password);
        }catch (DomainException e)
        {
            errors.add(e.getMessage());
        }

        try {
            person.setFirstName(firstname);
        }catch (DomainException e)
        {
            errors.add(e.getMessage());
        }

        try {
            person.setLastName(lastname);
        }catch (DomainException e)
        {
            errors.add(e.getMessage());
        }

        if(!errors.isEmpty())
        {
            request.setAttribute("erros",errors);
            return "accountForm";
        }

        super.getService().addPerson(person);
        HttpSession session = request.getSession();
        session.setAttribute("user", person);
        request.setAttribute("users",getService().getPersons());
        return "overview.jsp";

    }
}
