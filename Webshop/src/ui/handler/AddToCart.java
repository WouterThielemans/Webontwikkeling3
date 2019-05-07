package ui.handler;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class AddToCart extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ArrayList<Product> products;
        if (session.getAttribute("productscart") != null){
            products = (ArrayList<Product>)session.getAttribute("productscart");
        }else {
            products = new ArrayList<>();
        }
        String sessionId = session.getId();
        String productid = request.getParameter("productid");
        Product product = getService().getProduct(productid);
        products.add(product);
        session.setAttribute("productscart", products);
        request.setAttribute("productenLijst", getService().getAllProducts());
        return "productoverview.jsp";
    }
}