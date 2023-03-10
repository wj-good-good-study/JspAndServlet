package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "new_message", value = "/new_message")
public class new_message extends HttpServlet {
    private final String MEMBER_PATH="member";
    private final String Login_PATH="Gossip/index.html";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username=(String)request.getSession().getAttribute("login");
        if(username==null){
            response.sendRedirect(Login_PATH);
            return;
        }
        response.setContentType("text/html;charset=UTF-8");
        String pretext = new String(request.getParameter("pretext").getBytes("iso-8859-1"), "utf-8");
        if(pretext==null || pretext.length()==0){
            response.sendRedirect(MEMBER_PATH);
            return;
        }
        addmessage(username,pretext);
        request.getRequestDispatcher(MEMBER_PATH).forward(request,response);
    }
    private void addmessage(String username,String pretext){
        Date nowtime=new Date();
        SimpleDateFormat matter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("" + e);
        }
        try{
            String url="jdbc:mysql://127.0.0.1:3306/java?serverTimezone=GMT%2B8";
            String sqlusername="root";
            String sqlpassword="sql2008";
            con= DriverManager.getConnection(url,sqlusername,sqlpassword);
            Statement stmt=con.createStatement();
            int rs=stmt.executeUpdate("insert into message (username,text,time) values('"+username+"','"+pretext+"','"+ matter1.format(nowtime)+"');");
            con.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
