package nl.bioinf.meggink.webbased.servlets;

import nl.bioinf.meggink.webbased.config.WebConfig;
import nl.bioinf.meggink.webbased.model.CollectionClass;
import nl.bioinf.meggink.webbased.model.History;
import nl.bioinf.meggink.webbased.model.Penguin;
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
import java.util.Locale;

@WebServlet(name = "DetailsServlet", urlPatterns = "/species.detail")
public class DetailsServlet extends HttpServlet {
    private TemplateEngine templateEngine;

    @Override
    public void init() throws ServletException {
        this.templateEngine = WebConfig.createTemplateEngine(getServletContext());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String filename = "/Users/Marijke/Studie/Thema10/Webbased/webbased/data/species.csv";
        String species = request.getParameter("species");
        List<Penguin> penguins = CollectionClass.parsePenguin(filename);
        HttpSession session = request.getSession();
        History history;
        if (session.getAttribute("history") == null){
            history = new History();
            session.setAttribute("history", history);
        } else {
            history = (History)session.getAttribute("history");
        }

        WebConfig.configureResponse(response);
        Locale locale = request.getLocale();
        WebContext ctx = new WebContext(
                request,
                response,
                request.getServletContext(),
                locale);

        for (Penguin penguin: penguins){
            String name = penguin.getSpecies();
            if (name.equals(species)){
                history.addItem(penguin);
                ctx.setVariable("listing", history.getHistory());
                ctx.setVariable("penguin", penguin);
            }
        }

        templateEngine.process("species", ctx, response.getWriter());
    }
}
