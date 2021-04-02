package nl.bioinf.meggink.webbased.servlets;

import nl.bioinf.meggink.webbased.config.WebConfig;
import nl.bioinf.meggink.webbased.model.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "SpeciesServlet", urlPatterns = "/home")
public class SpeciesServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException{
        this.templateEngine = WebConfig.getTemplateEngine();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final CollectionInt dataSource = Factory.getDataSource();

        HttpSession session = request.getSession();
        History history;
        List<Penguin> historyList = new LinkedList<>();
        if (session.getAttribute("history") != null){
            history = (History)session.getAttribute("history");
            historyList = history.getHistory();
        }

        try {
            final List<Penguin> penguins = dataSource.getPenguins();

            Locale locale = request.getLocale();
            WebContext ctx = new WebContext(
                    request,
                    response,
                    request.getServletContext(),
                    locale);
            ctx.setVariable("penguins", penguins);
            ctx.setVariable("historyList", historyList);
            templateEngine.process("species-listing", ctx, response.getWriter());

        } catch (DatabaseException e){
            e.printStackTrace();
        }
    }
}
