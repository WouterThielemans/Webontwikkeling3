package ui.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class DeletePerson extends OverviewPerson {


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String id = request.getParameter("personId");
        getService().deletePerson(id);
        return showPersonOverview(request);
    }
}
