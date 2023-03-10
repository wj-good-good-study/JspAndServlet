package myServlet.Gossip;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "Register", value = "/Register")
public class Register extends HttpServlet {
    private final String USERS = "D:/idea-java/gossip/users";
    private final String SUCCESS_PATH = "RegisterSuccess";
    private final String ERROR_PATH = "RegisterError";
    private final Pattern emailRegex = Pattern.compile("^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$");
    private final Pattern passwordRegex = Pattern.compile("^\\w{8,16}$");
    private final Pattern usernameRegex = Pattern.compile("^\\w{1,16}$");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        List<String> errors = new ArrayList<>();
        if(!validateEmail(email)){
            errors.add("未填写邮箱或格式不正确！");
        }
        if(!validateUsername(username)){
            errors.add("未填写用户名或格式不正确！");
        }
        if(!validatePassword(password1,password2)){
            errors.add("请确认密码符合格式并确认密码！");
        }

        String path=null;
//        if (errors.isEmpty()){
//            path=SUCCESS_PATH;
//            tryCreateUser(email,username,password1);
//        }else{
//            path=ERROR_PATH;
//            request.setAttribute("errors",errors);
//        }
        Connection con;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println("" + e);
        }
        if(errors.isEmpty()){
            try{
                String url="jdbc:mysql://127.0.0.1:3306/java?serverTimezone=GMT%2B8";
                String sqlusername="root";
                String sqlpassword="sql2008";
                con= DriverManager.getConnection(url,sqlusername,sqlpassword);
                sqlusers u1=new sqlusers(username,password1,email);
                sql s1=new sql(con);
                if(s1.insert("java.users",u1)){
                    path=SUCCESS_PATH;
                }else{
                    path=ERROR_PATH;
                    errors.add("该用户已存在，无法注册！");
                    request.setAttribute("errors",errors);
                }
                s1.closeCon();
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }else{
            path=ERROR_PATH;
            request.setAttribute("errors",errors);
        }
        request.getRequestDispatcher(path).forward(request,response);
        System.out.println(email+username+password1+"-"+path);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    private boolean validateEmail(String email) {
        return email != null && emailRegex.matcher(email).find();
    }

    private boolean validateUsername(String username) {
        return username != null && usernameRegex.matcher(username).find();
    }

    private boolean validatePassword(String password1,String password2){
        return password1 != null && passwordRegex.matcher(password1).find() && password1.equals(password2);
    }

//    private void tryCreateUser(String email,String username,String password) throws IOException{
//        Path userhome = Paths.get(USERS,username);
//        if(Files.notExists(userhome)){
//            createUser(userhome,email,password);
//        }
//    }
//
//    private void createUser(Path userhome,String email,String password) throws IOException{
//        Files.createDirectories(userhome);
//        int salt = (int)(Math.random()*100);
//        String encrypt=String.valueOf(salt+password.hashCode());
//        Path profile=userhome.resolve("profile");
//        try(BufferedWriter writer=Files.newBufferedWriter(profile)){
//            writer.write(String.format("%s\t%s\t%d",email,encrypt,salt));
//        }
//    }
}
class sql{
    Connection con;
    sql(Connection con){
        this.con=con;
    }

    public boolean insert(String tablename,sqlusers user) throws SQLException {
        Statement stmt=this.con.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select * from "+tablename+" where username = '"+user.getName()+"'");
        if (!rs.next()){
            String insert="insert into "+tablename+" values('"+user.getName()+"','"+user.getPassword()+"','"+user.getEmail()+"')";
            int ok=stmt.executeUpdate(insert);
            return true;
        }else {
            return false;
        }
    }
    public boolean check(String tablename,sqlusers user) throws SQLException {
        System.out.println("user:"+user.getName()+","+user.getPassword());
        Statement stmt=this.con.createStatement();
        ResultSet rs;
        rs=stmt.executeQuery("select * from "+tablename+" where username = '"+user.getName()+"' && password= '"+user.getPassword()+"'");
        if(!rs.next()){
            return false;
        }else {
            return true;
        }
    }
    public void closeCon() throws SQLException {
        this.con.close();
    }
}

class sqlusers{
    String username;
    String password;

    String email;

    public sqlusers(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public sqlusers(String username, String password,String email) {
        this.username = username;
        this.password = password;
        this.email=email;
    }

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

}
