package lt.irmantasm.jsp1.controllers;

import lt.irmantasm.jsp1.model.Person;
import lt.irmantasm.jsp1.services.PersonService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "startup", value = "/startup", loadOnStartup = 1)
public class PersonController extends HttpServlet {

    @Inject
    PersonService personService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Person> people = personService.retrievePersons();
        req.setAttribute("persons", people);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("first.jsp");
        requestDispatcher.forward(req, resp);
    }
}
