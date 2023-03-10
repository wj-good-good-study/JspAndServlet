package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(
        name = "Login",
        urlPatterns = {"/Login"},
        initParams = {
                @WebInitParam(name="SUCCESS",value="member"),
                @WebInitParam(name="ERROR",value="/JspAndServlet/Gossip/index.html")
        }
)
public class Login extends HttpServlet {
//    private final String USERS = "D:/idea-java/gossip/users";
    private String SUCCESS_PATH;
    private String ERROR_PATH ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void init(){
        SUCCESS_PATH=getInitParameter("SUCCESS");
        ERROR_PATH=getInitParameter("ERROR");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection con;
        String page;
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
            sqlusers u1=new sqlusers(username,password);
            sql s1=new sql(con);
            if(s1.check("java.users",u1)){
                if(request.getSession(false)!=null){
                    request.changeSessionId();
                }
                request.getSession().setAttribute("login",username);
                page=SUCCESS_PATH;
            }
            else{
                page=ERROR_PATH;
            }
            response.sendRedirect(page);
            s1.closeCon();
        }
        catch(SQLException e) {
            System.out.println(e);
        }
    }





//    private boolean login(String username, String password) throws IOException {
//        if (username != null && username.trim().length() != 0 && password != null) {
//            Path userhome = Paths.get(USERS, username);
//            return Files.exists(userhome) && isCorrectPassword(password, userhome);
//        }
//        return false;
//    }
//
//    private boolean isCorrectPassword(String password, Path userhome) throws IOException {
//        Path profile = userhome.resolve("profile");
//        try (BufferedReader reader = Files.newBufferedReader(profile)) {
//            String[] data = reader.readLine().split("\t");
//            int encrypt = Integer.parseInt(data[1]);
//            int salt = Integer.parseInt(data[2]);
//            return password.hashCode() + salt == encrypt;
//        }
//    }
}
