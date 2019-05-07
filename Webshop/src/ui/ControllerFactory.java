package ui;

import domain.service.ShopService;
import ui.handler.RequestHandler;

public class ControllerFactory {

    public RequestHandler getController(String key, ShopService model){
        return createHandler(key,model);
    }

    private RequestHandler createHandler(String handlerName, ShopService model) {
        RequestHandler handler;
        try{
            Class<?> handlerClass = Class.forName("ui.handler."+ handlerName);
            Object handlerObject = handlerClass.getConstructor().newInstance();
            handler = (RequestHandler) handlerObject;
            handler.setService(model);
        } catch (Exception e) {
            throw new RuntimeException("The requested page does not exist.");
        }
        return  handler;
    }
}
