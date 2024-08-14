package ru.koryruno.springbootstartert1.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogConsoleFilterTest {

    @Test
    void When_TruncateResult_whenResultExceedsMaxLength() {
        String longString = "a".repeat(1500);
        String truncatedResult = LogConsoleFilter.truncateResult(longString);
        String expectedTruncatedResult = "a".repeat(1000) + "... [TRUNCATED]";

        assertEquals(expectedTruncatedResult, truncatedResult);
    }

    @Test
    void testTruncateResult_whenResultIsExactlyMaxLength() {
        String exactLengthString = "a".repeat(1000);
        String result = LogConsoleFilter.truncateResult(exactLengthString);

        assertEquals(exactLengthString, result);
    }

    @Test
    void testTruncateResult_whenResultIsShorterThanMaxLength() {
        String shortString = "short string";
        String result = LogConsoleFilter.truncateResult(shortString);

        assertEquals(shortString, result);
    }

    @Test
    void testTruncateResult_whenResultIsNull() {
        String result = LogConsoleFilter.truncateResult(null);

        assertEquals("null", result);
    }

}
