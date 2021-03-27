package nl.bioinf.meggink.webbased.servlets;

import nl.bioinf.meggink.webbased.config.WebConfig;
import nl.bioinf.meggink.webbased.model.User;
import nl.bioinf.meggink.webbased.model.UserDatabase;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        this.templateEngine = WebConfig.createTemplateEngine(getServletContext());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());

        HttpSession session = request.getSession();

        if (session.isNew() || session.getAttribute("user") == null) {
            boolean authenticated = UserDatabase.authenticate(username, password);
            if (authenticated) {
                session.setAttribute("user", new User(username, password));
                response.sendRedirect(request.getContextPath() + "/home");
            } else {
                ctx.setVariable("message", "Your password and/or username are incorrect, please try again");
                ctx.setVariable("message_type", "error");
                templateEngine.process("login", ctx, response.getWriter());
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                request.getLocale());
        ctx.setVariable("message", "Fill out the login form");
        ctx.setVariable("message_type", "info");
        templateEngine.process("login", ctx, response.getWriter());
    }
}
