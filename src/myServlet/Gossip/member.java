package myServlet.Gossip;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "member", value = "/member")
public class member extends HttpServlet {
    private final String MEMBER_PATH="memberview";
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
        HashMap<String,List> messages=getmessage(username);
        request.setAttribute("messages",messages);
        request.getRequestDispatcher(MEMBER_PATH).forward(request,response);

    }

    private HashMap<String,List> getmessage(String username){
        HashMap<String,List> table=new HashMap<String,List>();
        List<message> l1=new ArrayList<>();
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
            ResultSet rs;
            rs=stmt.executeQuery("select * from message where username='"+username+"'");
            while(rs.next()){
                String mytext=rs.getString(2);
                Date mydate=rs.getDate(3);
                Time mytime=rs.getTime(3);
                message m1=new message(mytext,mydate,mytime);
                l1.add(m1);
            }
            table.put(username,l1);
            con.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return table;
    }

}




