package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RegisterError", value = "/RegisterError")
public class RegisterError extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>新增会员失败</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>新增会员失败!</h1>");
        out.println("<ul style='color:rgb(255,0,0);'>");
        List<String> errors=(List<String>)request.getAttribute("errors");
        errors.forEach(error->out.printf("<li>%s</li>",error));
        out.println("<a href='/JspAndServlet/Gossip/index.html'>回首页</a>");
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }
}
