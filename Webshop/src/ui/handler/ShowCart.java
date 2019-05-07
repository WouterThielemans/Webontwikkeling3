package ui.handler;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class ShowCart extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("productscart") == null){
            request.setAttribute("emp", "The cart is empty");
        }else {
            ArrayList<Product> product = (ArrayList<Product>)session.getAttribute("productscart");
            request.setAttribute("productscart", product);
        }
        return "shoppingcart.jsp";
    }
}
