package ui.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return index(request);
    }

    private String index(HttpServletRequest request){
        if(getQuotePref(request).equals("yes")){
            request.setAttribute("quoteTekst", "It’s Not A Bug – It’s An Undocumented Feature.");
        }
        return "index.jsp";
    }

    private String getQuotePref(HttpServletRequest request){
        Cookie[]cookies = request.getCookies();
        String value="";
        if(cookies != null){
            for(Cookie c: cookies){
                if(c.getName().equals("quote")){
                    value=c.getValue();
                }
            }
        }
        return value;
    }


}



