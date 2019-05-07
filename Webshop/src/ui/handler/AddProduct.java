package ui.handler;

import domain.model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class AddProduct extends OverviewProduct {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Product product = new Product();

        List<String> result = new ArrayList<String>();
        getName(product,request,result);
        getDescription(product,request,result);
        getPrice(product,request,result);
        getId(product,request,result);

        String destination="addProduct.jsp";
        if (result.size() > 0) {
            request.setAttribute("result", result);
            destination = "productoverview.jsp";
        }

        else {
            try{
                getService().addProduct(product);
                destination = showProductOverview(request);
            }
            catch (Exception exc){
                result.add(exc.getMessage());
                request.setAttribute("result", result);
            }
        }
        return "productoverview.jsp";
    }

    private void getId(Product product, HttpServletRequest request, List<String> result) {

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
}
