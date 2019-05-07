package ui.handler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetQuote extends Home {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String quote = request.getParameter("quote");
        Cookie cookie = new Cookie("quote", quote);
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        if(quote.equals("yes")){
            request.setAttribute("quoteTekst", "It’s Not A Bug – It’s An Undocumented Feature.");
        }
        return "index.jsp";
    }
}

