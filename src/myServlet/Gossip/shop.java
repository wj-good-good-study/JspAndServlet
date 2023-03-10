package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "shop", value = "/shop")
public class shop extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String username=request.getParameter("username");
        int bookid=Integer.parseInt(request.getParameter("bookid"));
        String bookname=request.getParameter("bookname");
        Double price=Double.parseDouble(request.getParameter("price"));
        Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("" + e);
        }
        try {
            String url = "jdbc:mysql://127.0.0.1:3306/java?serverTimezone=GMT%2B8";
            String sqlusername = "root";
            String sqlpassword = "sql2008";
            con = DriverManager.getConnection(url, sqlusername, sqlpassword);
            Statement stmt=con.createStatement();
            int ok=stmt.executeUpdate("insert into orders(username,bookname,price,quantity) values ("+username+",'"+bookname+"',"+price+","+1+")" );
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("shoppingview").include(request,response);
    }
}
