package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "shopcar", value = "/shopcar")
public class shopcar extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("<h1>这是购物车</h1>");
        String host=request.getParameter("host");
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
            ResultSet rs;
            rs=stmt.executeQuery("select bookname,count(*),price,sum(amount) as smt from orders where username="+host+" group by username,bookname;");
            while(rs.next()){
                String bookname=rs.getString(1);
                int count=rs.getInt(2);
                Double price=rs.getDouble(3);
                Double amount=rs.getDouble(4);
                out.print("<div style='width:300px;border:1px solid black;height:auto;margin:20px;padding:10px;'>");
                out.print("课本名称："+bookname+"<br>");
                out.print("单价："+price+"元<br>");
                out.print("共："+count+"本<br>");
                out.print("共："+amount+"元<br>");
                out.print("</div>");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
