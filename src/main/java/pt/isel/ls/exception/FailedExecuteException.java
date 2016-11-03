package pt.isel.ls.exception;

public class FailedExecuteException extends Exception {

    public FailedExecuteException(String error) {
        System.out.println(error);
    }
}
