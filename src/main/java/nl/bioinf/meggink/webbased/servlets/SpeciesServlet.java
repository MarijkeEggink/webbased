package nl.bioinf.meggink.webbased.servlets;

import nl.bioinf.meggink.webbased.config.WebConfig;
import nl.bioinf.meggink.webbased.model.CollectionClass;
import nl.bioinf.meggink.webbased.model.Penguin;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "SpeciesServlet", urlPatterns = "/home")
public class SpeciesServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException{
        this.templateEngine = WebConfig.createTemplateEngine(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "/Users/Marijke/Studie/Thema10/Webbased/webbased/data/species.csv";
        List<Penguin> penguins = CollectionClass.parsePenguin(filename);
        System.out.println("Number of penguins is: " + penguins.size());

        Locale locale = request.getLocale();
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                locale);
        ctx.setVariable("penguins", penguins);
        templateEngine.process("species-listing", ctx, response.getWriter());

    }
}
