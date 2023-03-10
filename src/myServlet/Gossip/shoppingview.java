package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;

@WebServlet(name = "shoppingview", value = "/shoppingview")
public class shoppingview extends HttpServlet {
    HashMap<String,books> table=new HashMap<>();
    String host;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("</head>");
        out.println("<body>");
        host=(String)request.getSession().getAttribute("login");
        out.printf("<h1>欢迎%s来到课本商品界面</h1><br>", host);
        //连接数据库展示书本信息
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
            rs=stmt.executeQuery("select count(*) from orders where username='"+host+"'");
            while(rs.next()){
                String count=rs.getString(1);
                out.println("<a href='shopcar?host=\""+host+"\"'>购物车中有"+count+"本书</a><br>");
            }
            rs=stmt.executeQuery("select * from books");
            while(rs.next()){
                int bookid=rs.getInt(1);
                String bookname=rs.getString(2);
                Double price=rs.getDouble(3);
                out.print("<div style='float:left;width:200px;border:1px solid black;height:150px;margin:20px;'>");
                out.print("课本编号："+bookid+"<br>");
                out.print("课本名称："+bookname+"<br>");
                out.print("价格："+price+"<br>");
                out.printf("<a href='shop?username=\""+host+"\"&bookid="+bookid+"&bookname=\""+bookname+"\"&price="+price+"'>点击购买</a><br>");
                out.print("</div>");
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        //退出登录
        out.println("<div style='clear:both;'><a href='Logout'>点击注销</a></div>");
        out.println("</body>");
        out.println("</html>");
    }
}
class books{
    int bookid;
    String bookname;
    Double price;
    books(int bookid,String bookname,Double price){
        this.bookid=bookid;
        this.bookname=bookname;
        this.price=price;
    }
}