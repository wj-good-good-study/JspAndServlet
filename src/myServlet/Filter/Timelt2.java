package myServlet.Filter;

import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class Timelt2 extends HttpFilter {
    private Logger logger = Logger.getLogger(Timelt2.class);
    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        long begin=current();
        chain.doFilter(request, response);
        /*getServletContext().log(
                String.format("Resquest process in %d milliseconds",current()-begin)
        );*/
        logger.info(String.format("Timelet2:Resquest process in %d milliseconds",current()-begin));
    }

    @Override
    public void destroy() {

    }

    private long current(){
        return System.currentTimeMillis();
    }
}
