package nl.bioinf.meggink.webbased.config;

import nl.bioinf.meggink.webbased.model.DatabaseException;
import nl.bioinf.meggink.webbased.model.Factory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletResponse;

@WebListener
public class WebConfig implements ServletContextListener {
    private static TemplateEngine templateEngine;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("[WebConfig] Initializing template engine");
        createTemplateEngine(servletContextEvent.getServletContext());

        String dbType = servletContextEvent.getServletContext().getInitParameter("db-type");
        try {
            Factory.initializeDataSource(dbType);
        } catch (DatabaseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }

    public static void createTemplateEngine(ServletContext servletContext) {
        ServletContextTemplateResolver templateResolver =
                new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);
        // Cache is set to true by default.
        // Set to false if you want templates to be automatically
        // updated when modified.
        templateResolver.setCacheable(true);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        WebConfig.templateEngine = templateEngine;
    }

    /**
     * Serves the template engine that was created at application startup.
     * @return template engine
     */
    public static TemplateEngine getTemplateEngine(){
        return templateEngine;
    }

    /**
     * Configures the response in a standard way.
     * @param response
     */
    public static void configureResponse(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    }

}
