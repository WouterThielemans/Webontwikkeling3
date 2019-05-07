package ui.handler;

import domain.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public abstract class RequestHandler {

    private ShopService service;

    public abstract String handleRequest(HttpServletRequest request, HttpServletResponse response) throws SQLException;

    public void setService(ShopService service) {
        this.service =service;
    }

    public ShopService getService(){
        return service;
    }
}
