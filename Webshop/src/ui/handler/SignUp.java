package ui.handler;

import domain.model.Person;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class SignUp extends OverviewPerson {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();

        List<String> result = new ArrayList<String>();
        getUserid(person, request, result);
        getFirstname(person, request, result);
        getLastName(person, request, result);
        getEmail(person, request, result);
        getPassword(person, request, result);

        String destination="signUp.jsp";
        if (result.size() > 0) {
            request.setAttribute("result", result);
            //destination = "signUp.jsp";
        }

        else {
            try{
                getService().addPerson(person);
                destination = showPersonOverview(request);
            }
            catch (Exception exc){
                result.add(exc.getMessage());
                request.setAttribute("result", result);
            }
        }


        return destination;
    }

    private void getPassword(Person person, HttpServletRequest request, List<String> result) {
        String password= request.getParameter("password");
        request.setAttribute("passwordPreviousValue",password);

        try {
            person.setPasswordHashed(password);
            request.setAttribute("passwordClass","has-succes");
        }
        catch (Exception exc){
            request.setAttribute("passwordClass","has-error");
            result.add(exc.getMessage());

        }
    }


    private void getFirstname(Person person, HttpServletRequest request, List<String> result ){
        String firstName= request.getParameter("firstName");
        request.setAttribute("firstNamePreviousValue",firstName);

        try {
            person.setFirstName(firstName);
            request.setAttribute("firstNameClass","has-succes");
        }
        catch (Exception exc){
            request.setAttribute("firstNameClass","has-error");
            result.add(exc.getMessage());

        }
    }
    private void getLastName(Person person, HttpServletRequest request, List<String> result ){
        String lastName= request.getParameter("lastName");
        request.setAttribute("lastNamePreviousValue",lastName);

        try {
            person.setLastName(lastName);
            request.setAttribute("lastNameClass","has-succes");
        }
        catch (Exception exc){
            request.setAttribute("lastNameClass","has-error");
            result.add(exc.getMessage());

        }
    }

    private void getUserid(Person person, HttpServletRequest request, List<String> result) {
        String userid= request.getParameter("userid");
        request.setAttribute("useridPreviousValue",userid);

        try {
            person.setUserid(userid);
            request.setAttribute("useridClass","has-succes");
        }
        catch (Exception exc){
            request.setAttribute("useridClass","has-error");
            result.add(exc.getMessage());

        }
    }

    private void getEmail(Person person, HttpServletRequest request, List<String> result ) {
        String email = request.getParameter("email");
        request.setAttribute("emailPreviousValue", email);

        try {
            person.setEmail(email);
            request.setAttribute("emailClass", "has-succes");
        } catch (Exception exc) {
            request.setAttribute("emailClass", "has-error");
            result.add(exc.getMessage());

        }
    }
}
