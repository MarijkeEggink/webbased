package nl.bioinf.meggink.webbased.model;

public class Factory {
    private static CollectionInt daoInstance;

    public static void initializeDataSource(String type) throws DatabaseException {
        if (daoInstance != null) {
            throw new IllegalStateException("DAO can be initialized only once");
        }
        switch (type) {
            case "dummy": {
                createDummyInstance();
                break;
            }
            case "mysql": {
                createMySQLInstance();
                break;
            }
            default: throw new IllegalArgumentException("unknown database type requested");
        }
    }

    public static CollectionInt getDataSource() {
        if (daoInstance == null) {
            throw new IllegalStateException("DAO is not initialized, call initializeDataSource() fist");
        }
        return daoInstance;
    }

    private static void createDummyInstance() throws DatabaseException {
        daoInstance = new CollectionClassMemory();
        daoInstance.connect();
    }

    private static void createMySQLInstance() throws DatabaseException {
        //pass
    }
}
