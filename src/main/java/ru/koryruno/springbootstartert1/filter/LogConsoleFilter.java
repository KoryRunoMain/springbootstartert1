package ru.koryruno.springbootstartert1.filter;

/**
 * Filter for logging results to the console with a maximum log length limit.
 *
 * <p>This class provides functionality to trim results to a given length to prevent log entries
 * from becoming too long, which can make reading and parsing difficult.</p>
 */
public class LogConsoleFilter {
    private static final int MAX_LOG_LENGTH = 1000;

    public static String truncateResult(Object result) {
        if (result == null) {
            return "null";
        }
        String resultString = result.toString();
        if (resultString.length() > MAX_LOG_LENGTH) {
            return resultString.substring(0, MAX_LOG_LENGTH) + "... [TRUNCATED]";
        }
        return resultString;
    }

}
