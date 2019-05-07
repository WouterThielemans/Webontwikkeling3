package ui.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProduct extends OverviewProduct{


    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("productid");
        getService().deleteProduct(id);
        return showProductOverview(request);
    }
}
