package nl.bioinf.meggink.webbased.model;

import java.util.List;

public interface CollectionInt {
    void connect() throws DatabaseException;

    void disconnect() throws DatabaseException;

    List<Penguin> getPenguins() throws DatabaseException;
}
