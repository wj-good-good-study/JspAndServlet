package myServlet.Model2practice;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "View", value = "/View")
public class View extends HttpServlet {
    private String template=
            "<!DOCTYPE html>"
            +"<html>"
            +"<head>"
            +"<meta charset='UTF-8'>"
            +"<title>%s</title>"
            +"</head>"
            +"<body>"
            +"<h1>%s</h1>"
            +"</body>"
            +"</html>";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user=request.getParameter("user");
        String animal=(String)request.getAttribute("animal");
        String html=String.format(template,user,animal);

        response.getWriter().print(html);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
