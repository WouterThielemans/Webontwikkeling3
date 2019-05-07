package ui;
import domain.model.Person;
import domain.model.Product;
import domain.service.ShopService;
import ui.handler.RequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@WebServlet("/ControllerOld")
public class ControllerOld extends HttpServlet {

    private ShopService service;

    private ControllerFactory controllerFactory = new ControllerFactory();

    @Override
    public void init () throws ServletException{
        super.init();
        ServletContext context = getServletContext();

        Properties properties = new Properties();
        properties.setProperty("user", context.getInitParameter("user"));
        properties.setProperty("password", context.getInitParameter("password"));
        properties.setProperty("ssl", context.getInitParameter("ssl"));
        properties.setProperty("sslfactory", context.getInitParameter("sslfactory"));
        properties.setProperty("sslmode", context.getInitParameter("sslmode"));
        properties.setProperty("currentSchema", context.getInitParameter("currentSchema"));
        properties.setProperty("url", context.getInitParameter("url"));

        /*	NOG BETER
         *
         *     Enumeration<String> parameterNames = context.getInitParameterNames();
         *     while (parameterNames.hasMoreElements()){
         *     		String propertyName = parameterNames.nextElement();
         *     		properties.setProperty(propertyName, context.getInitParameter(propertyName));
         *     }
         */

        service = new ShopService(properties);


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String command = "overview";
        String destination = "index.jsp";
        if (request.getParameter("command") != null) {
            command = request.getParameter("command");
            RequestHandler handler;
            handler = controllerFactory.getController(command,service);
            destination = handler.handleRequest(request,response);
        }
/*
        switch(command){
            case "home":
                destination = home(request, response);
                break;
            case "personOverview":
                destination = showPerson(request, response);
                break;
            case "productOverview":
                destination = showProduct(request,response);
                break;
            case "confirmDeleteProduct":
                destination = "confirmDeleteProduct.jsp";
                break;
            case "confirmDeletePerson":
                destination = "confirmDeletePerson.jsp";
                break;
            case "addProduct":
                destination = addProduct(request,response);
                break;
            case "delete":
                destination = delete(request,response);
                break;
            case "deletePerson":
                destination = deletePerson(request,response);
                break;
            case "signUp":
                destination = signUp(request, response);
                break;
            default:
                destination = home(request, response);
        }
*/
        request.getRequestDispatcher(destination).forward(request,response);
    }



    private String home(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }

    private String addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Product product = new Product();

        List<String> result = new ArrayList<String>();
        getName(product,request,result);
        getDescription(product,request,result);
        getPrice(product,request,result);

        String destination="addProduct.jsp";
        if (result.size() > 0) {
            request.setAttribute("result", result);
            destination = "addProduct.jsp";
        }

        else {
            try{
                service.addProduct(product);
                destination = showProduct(request, response);
            }
            catch (Exception exc){
                result.add(exc.getMessage());
                request.setAttribute("result", result);
            }
        }

        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request, response);
        return "addProduct.jsp";
    }

    private String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("productid");
        service.deleteProduct(id);
        return showProduct(request,response);
    }

    private String deletePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String id = request.getParameter("personId");
        service.deletePerson(id);
        return showPerson(request,response);
    }


    private void getPrice(Product product, HttpServletRequest request, List<String> result) {
        String price = request.getParameter("price");
        request.setAttribute("previousPrice",price);


        try {
            product.setPrice(price);
            request.setAttribute("priceClass","has-succes");
        }
        catch (Exception exc){
            request.setAttribute("priceClass","has-error");
            result.add(exc.getMessage());
        }
    }

    private void getDescription(Product product, HttpServletRequest request, List<String> result) {
        String description = request.getParameter("description");
        request.setAttribute("previousDescription", description);

        try{
            product.setDescription(description);
            request.setAttribute("descriptionClass", "has-succes");
        }
        catch(Exception exc){
            request.setAttribute("descriptionClass", "has-error");
            result.add(exc.getMessage());
        }
    }

    private void getName(Product product, HttpServletRequest request, List<String> result) {
        String name = request.getParameter("name");
        request.setAttribute("previousName",name);

        try{
            product.setName(name);
            request.setAttribute("nameClass", "has-succes");
        }
        catch(Exception exc){
            request.setAttribute("nameClass", "has-errors");
            result.add(exc.getMessage());
        }
    }

    private String showPerson (HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
        List<Person>personenLijst =service.getAllPersons() ;
        request.setAttribute("personenLijst",personenLijst);
        return "personoverview.jsp";
    }

    private String showProduct(HttpServletRequest request, HttpServletResponse response) throws  ServletException,IOException{
        List<Product>productenLijst = service.getAllProducts();
        request.setAttribute("productenLijst",productenLijst);
        return "productoverview.jsp";
    }

    private String signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                service.addPerson(person);
                destination = showPerson(request, response);
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
            person.setPassword(password);
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

    private void getEmail(Person person, HttpServletRequest request, List<String> result ){
        String email= request.getParameter("email");
        request.setAttribute("emailPreviousValue",email);

        try {
            person.setEmail(email);
            request.setAttribute("emailClass","has-succes");
        }
        catch (Exception exc){
            request.setAttribute("emailClass","has-error");
            result.add(exc.getMessage());

        }
    }

}
