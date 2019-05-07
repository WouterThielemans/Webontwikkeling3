package ui.handler;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class OverviewPerson extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        return showPersonOverview(request);
    }

    protected String showPersonOverview(HttpServletRequest request) {
        List<Person> personenLijst = getService().getAllPersons();
        request.setAttribute("personenLijst",personenLijst);
        return "personoverview.jsp";
    }
}
