package ru.koryruno.springbootstartert1.filter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogConsoleFilterTest {

    @Test
    void When_TruncateResult_IsOverMaxLength_Expect_Truncated() {
        String longString = "a".repeat(1500);
        String truncatedResult = LogConsoleFilter.truncateResult(longString);
        String expectedTruncatedResult = "a".repeat(1000) + "... [TRUNCATED]";

        assertEquals(expectedTruncatedResult, truncatedResult);
    }

    @Test
    void When_TruncateResult_IsEqualsMaxLength_Expect_Successfully() {
        String exactLengthString = "a".repeat(1000);
        String result = LogConsoleFilter.truncateResult(exactLengthString);

        assertEquals(exactLengthString, result);
    }

    @Test
    void When_TruncateResult_IsShorterThenMaxLength_Expect_Successfully() {
        String shortString = "short string";
        String result = LogConsoleFilter.truncateResult(shortString);

        assertEquals(shortString, result);
    }

    @Test
    void When_TruncateResult_IsNull_Expect_StringWithNullValue() {
        String result = LogConsoleFilter.truncateResult(null);

        assertEquals("null", result);
    }

}
