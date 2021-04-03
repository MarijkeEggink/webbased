package nl.bioinf.meggink.webbased.servlets;

import com.google.gson.Gson;
import nl.bioinf.meggink.webbased.model.CollectionClass;
import nl.bioinf.meggink.webbased.model.Penguin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "InfoServlet", urlPatterns = "/species_info")
public class InfoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Penguin> allPenguins = CollectionClass.getPenguins();
        String requestType = request.getParameter("info");
        String requestName = request.getParameter("species");
        response.setContentType("application/json;charset=UTF-8");
        final PrintWriter writer = response.getWriter();
        JsonResponse jsonResponse = new JsonResponse();
        if (requestType.equals("short")) {
            List<Penguin> penguins = new ArrayList<>();
            for (Penguin penguin: allPenguins){
                String species = penguin.getSpecies();
                if (species.contains(requestName)) {
                    penguins.add(penguin);
                }
            }
            jsonResponse.responseType = "species";
            jsonResponse.responseObject = penguins;
        } else if (requestType.equals("long")){
            jsonResponse.errorMessage = "this is not implemented yet";
        } else {
            jsonResponse.errorMessage = "unknown request_type: " + requestType;
        }
        Gson gson = new Gson();
        writer.write(gson.toJson(jsonResponse));
        writer.flush();
    }

    private static class JsonResponse {
        String errorMessage = "NO ERRORS";
        String responseType = "species";
        Object responseObject;
    }
}
