package ui.handler;

import domain.model.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OverviewProduct extends RequestHandler {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return showProductOverview(request);
    }

    protected String showProductOverview(HttpServletRequest request) {
        List<Product> productenLijst = getService().getAllProducts();
        request.setAttribute("productenLijst",productenLijst);
        return "productoverview.jsp";
    }
}
