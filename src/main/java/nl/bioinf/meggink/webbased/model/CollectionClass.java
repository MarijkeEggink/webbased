package nl.bioinf.meggink.webbased.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CollectionClass {
    public static List<Penguin> parsePenguin(String filename){
        Path path = Paths.get(filename);

        List<Penguin> penguins = new ArrayList<>();

        try (BufferedReader bufferedReader = Files.newBufferedReader(path)){
            String line;
            int lineNumber = 0;

            while ((line = bufferedReader.readLine()) != null){
                lineNumber ++;
                if (lineNumber > 1){
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
        return penguins;
    }
}
