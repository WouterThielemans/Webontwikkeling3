package ui.handler;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class IsCorrectPassword extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person= null;
        try {
            person = getService().getPerson(request.getParameter("id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String teCheckenPassword= request.getParameter("checkPassword");
        try {
            if (person.isCorrectPassword(teCheckenPassword)){
                request.setAttribute("isCorrect","correct");
            }
            else {
                request.setAttribute("isCorrect","not correct");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "checkPasswordResult.jsp";
    }
}
