package myServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ninenine_table", value = "/ninenine_table")
public class ninenine_table extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name=request.getParameter("name");
        PrintWriter out=response.getWriter();
        out.print("<!DOCUMENT html");
        out.print("<html>");
        out.print("<head>");
        out.print("<body>");
        for (int i=1;i<=9;i++){
            for (int j = i; j <=9 ; j++) {
                out.printf("%d*%d=",i,j);
                out.print("<span style='display:inline-block;width:40px;'>");
                out.print(i*j);
                out.print("</span>");
            }
            out.print("<br>");
        }
        out.print("</body>");
        out.print("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
