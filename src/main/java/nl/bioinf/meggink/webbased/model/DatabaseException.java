package nl.bioinf.meggink.webbased.model;

public class DatabaseException extends Exception{
    public DatabaseException() { super(); }

    public DatabaseException(String message) { super(message); }

    public DatabaseException(String message, Throwable cause) { super(message, cause); }

    public DatabaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
