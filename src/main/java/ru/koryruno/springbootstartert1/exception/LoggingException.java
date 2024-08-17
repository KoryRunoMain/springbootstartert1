package ru.koryruno.springbootstartert1.exception;

public class LoggingException extends Throwable {

    public LoggingException() {
    }

    public LoggingException(String message, long duration, String errorTime) {
        super(String.format("%s (Duration: %dms, Time: %s)", message, duration, errorTime));
    }

}
