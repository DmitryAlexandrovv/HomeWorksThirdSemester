import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MyServlet extends HttpServlet{
    CSVService csvService = new CSVService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String name = req.getParameter("fullName");
            String password = req.getParameter("password");
            String passwordConf = req.getParameter("passwordConf");
            String checkbox = req.getParameter("checkbox");

            User user = new User(email, name, password, passwordConf, checkbox);

            csvService.writeUser(user);
            registrationSuccess(req, resp);
        } catch (Exception e) {
            registrationFiled(req, resp);
        }
    }

    protected String registrationFiled(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("static/registrationFailed.html");

        view.forward(req, resp);
        return null;
    }

    protected String registrationSuccess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view = req.getRequestDispatcher("static/registrationSuccess.html");

        view.forward(req, resp);
        return null;
    }
}
