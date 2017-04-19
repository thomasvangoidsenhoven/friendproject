package controller;

import service.PersonService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 16/03/2017.
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet
{
    private PersonService model = new PersonService();

    private ControllerFactory controllerFactory = new ControllerFactory();

    public Controller()
    {
        super();
    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String destination = "index.jsp";
        if (action != null) {
            RequestHandler handler;
            try {
                handler = controllerFactory.getController(action, model);
                destination = handler.handleRequest(request, response);
                if(destination.equals("async")) return;
            }
            catch (NotAuthorizedException exc) {
                List<String> errors = new ArrayList<String>();
                errors.add(exc.getMessage());
                request.setAttribute("errors", errors);
                destination="index.jsp";
            }
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
    }



}
