package myServlet.Gossip;

import myServlet.Listener.OnlineUsers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "memberview", value = "/memberview")
public class memberview extends HttpServlet {
    private final String Login_PATH="Gossip/index.html";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String username=(String)request.getSession().getAttribute("login");
        if(username==null){
            response.sendRedirect(Login_PATH);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<title>Gossip微博</title>");
        out.println("</head>");
        out.println("<body>");
        out.printf("<h1>当前有%d人在线</h1>", OnlineUsers.counter);
        out.printf("<a href='Logout'>注销%s</a><br>",username);
        out.println("<a href='shoppingview'>前往购物车</a><br>");
        out.println("<div>");
        out.println("分享新鲜事......<br>");
        out.println("<form method='post' action='new_message'>");
        out.println("<textarea cols='60' rows='6' name='pretext'></textarea><br/>");
        out.println("<input  type='submit' value='发送'>");
        out.println("</form>");
        HashMap<String, List> map=(HashMap<String, List>) request.getAttribute("messages");
        List<message> list1=map.get(username);
        for (message m : list1){
            out.print("<hr/>");
            out.printf("<p>%s：</p>",username);
            out.printf("<p>%s</p>",m.text);
            out.printf("<p>%s %s</p>",m.date,m.time);
            out.println("<form method='post' action='del_message'>");
            out.printf("<input type='hidden' name='millis' value='%s'>",m.date+" "+m.time);
            out.printf("<button type='submit' >删除</button>");
            out.println("</form>");
            out.print("<hr/>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
