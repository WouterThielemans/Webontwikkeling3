package ui.handler;

import domain.db.Role;
import domain.model.Person;
import domain.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class LogIn extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        HttpSession session = request.getSession();
        if (session.getAttribute("role") != null){
            session.removeAttribute("role");
        }
        try {
            if(getService().(id)){
                session.setAttribute("role", Role.ADMIN);
            }else{
                session.setAttribute("role", Role.CUSTOMER);
            }
    }
}
