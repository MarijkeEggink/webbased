package nl.bioinf.meggink.webbased.model;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class CollectionClass implements ServletContextListener {
    private static List<Penguin> penguins;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("[CollectionClass] Initializing penguins");
        String fileName = servletContextEvent.getServletContext().getInitParameter("filename");
        createCollectionClass(fileName);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Shutting down!");
    }

    public static void createCollectionClass(String fileName){
        Path path = Paths.get(fileName);

        List<Penguin> penguins = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)){
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null){
                lineNumber ++;
                if(lineNumber > 1){
                    String[] elements = line.split(";");
                    String scientificName = elements[0];
                    String englishName = elements[1];
                    String dutchName = elements[2];
                    int length = Integer.parseInt(elements[3]);
                    int weight = Integer.parseInt(elements[4]);
                    int age = Integer.parseInt(elements[5]);
                    String picture = elements[6];
                    Penguin penguin = new Penguin(scientificName, englishName, dutchName, length, weight, age, picture);
                    penguins.add(penguin);
                }
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        CollectionClass.penguins = penguins;
    }

    public static List<Penguin> getPenguins(){
        return penguins;
    }
}
