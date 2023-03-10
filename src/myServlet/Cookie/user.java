package myServlet.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@WebServlet(name = "user", value = "/user")
public class user extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Cookie> userCookie=Optional.ofNullable(request.getCookies()).flatMap(this::userCookie);
        if (userCookie.isPresent()){
            Cookie cookie=userCookie.get();
            request.setAttribute(cookie.getName(), cookie.getValue());
            request.getRequestDispatcher("userview").forward(request,response);
        }else{
            response.sendRedirect("login.html");
        }
    }
    private Optional<Cookie> userCookie(Cookie[] cookies){
        return Stream.of(cookies).filter(cookie->check(cookie)).findFirst();
    }

    private boolean check(Cookie cookie){
        return "user".equals(cookie.getName()) || "tom".equals(cookie.getValue());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
