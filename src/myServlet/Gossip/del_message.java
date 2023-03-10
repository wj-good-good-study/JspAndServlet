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

@WebServlet(name = "del_message", value = "/del_message")
public class del_message extends HttpServlet {
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
        String millis=request.getParameter("millis");
        if(millis!=null){
            deletemessage(username,millis);
        }
        response.sendRedirect(MEMBER_PATH);
    }
    private void deletemessage(String username,String millis){
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
            int rs=stmt.executeUpdate("delete from message where username='"+username+"' && time= '"+millis+"'");
            con.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
}
