package net.geekhour.springreview.exception;

public class InvalidParameterException extends RuntimeException {
    public InvalidParameterException(String message){
        super(message);
    }
}
