import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        System.out.println(req.getHeader("User-Agent"));
        if(req.getHeader("User-Agent").indexOf("Edg") != -1){
            RequestDispatcher view = req.getRequestDispatcher("static/edgeUser.html");
            view.forward(req, res);
        }else {
            chain.doFilter(req, res);
        }
    }
}
