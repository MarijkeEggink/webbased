package nl.bioinf.meggink.webbased.model;

import java.util.ArrayList;
import java.util.List;

public class CollectionClassMemory implements CollectionInt {
    private List<Penguin> penguinsDB = new ArrayList<>();

    public CollectionClassMemory() { creatDB(); }

    @Override
    public void connect() throws DatabaseException {
        //pass silently
    }

    @Override
    public void disconnect() throws DatabaseException {
        //pass silently
    }

    @Override
    public List<Penguin> getPenguins() throws DatabaseException {
        return this.penguinsDB;
    }

    private void creatDB(){
        penguinsDB.add(new Penguin("Aptenodytes forsteri", "Emperor penguin", "Keizerspinguin", 110, 30, 20, "figures/Emperor.jpg"));
        penguinsDB.add(new Penguin("Eudyptes chrysocome", "Souther rockhopper penguin", "Zuidelijke rotspinguin", 56, 3, 10, "figures/Rockhopper.jpg"));
        penguinsDB.add(new Penguin("Eudyptala minor", "Little penguin", "Dwergpinguin", 35, 1, 6, "figures/Little.jpg"));
        penguinsDB.add(new Penguin("Pygoscelis papua", "Gentoo penguin", "Ezelspenguin", 75, 6, 13, "figures/Gentoo.jpg"));
        penguinsDB.add(new Penguin("Spheniscus demersus", "Afrikan penguin", "Zwartvoetpinguin", 65, 3, 20, "figures/Afrikan.jpg"));
    }
}
