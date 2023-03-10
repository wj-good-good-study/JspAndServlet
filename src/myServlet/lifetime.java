package myServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "lifetime", value = "/lifetime")
public class lifetime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        String lifetime=request.getParameter("lifetime");
        System.out.println("打开网页时都会执行doGet()函数");
        out.println("生命周期函数");

    }
    public void init() throws ServletException{
        System.out.println("服务器启动时调用了init()函数");
    }
    public void destroy(){
        System.out.println("关闭服务器时才会调用destory()函数");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
