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


@WebServlet("/Controller")
public class Controller extends HttpServlet {

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
        String command = request.getParameter("command");
        String destination = "index.jsp";
        if (request.getParameter("command") != null) {
            RequestHandler handler;
            handler = controllerFactory.getController(command,service);
            destination = handler.handleRequest(request,response);
        }
        request.getRequestDispatcher(destination).forward(request,response);
    }
}
