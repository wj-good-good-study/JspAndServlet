package myServlet.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name=request.getParameter("username");
        String password=request.getParameter("password");
        String page;
        if("tom".equals(name) && "123456".equals(password)){
            processCookie(request,response);
            page="user";
        }else {
            page="login.html";
        }
        response.sendRedirect(page);
    }
    private void processCookie(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie=new Cookie("user","tom");
        if("true".equals(request.getParameter("auto"))){
            cookie.setMaxAge(7*24*60*60);
        }
        response.addCookie(cookie);
    }

}
