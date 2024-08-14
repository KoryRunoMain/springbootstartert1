package ru.koryruno.springbootstartert1.filter;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HttpLoggingTypeTest {

    private static final String VALID_FULL = "FULL";
    private static final String VALID_BASIC = "BASIC";
    private static final String INVALID = "INVALID";
    private static final String EMPTY = "";

    @Test
    void WhenCalled_GivenValidString_Expect_OptionalWithEnum() {
        Optional<HttpLoggingType> resultFull = HttpLoggingType.from(VALID_FULL);
        Optional<HttpLoggingType> resultBasic = HttpLoggingType.from(VALID_BASIC);

        assertThat(resultFull).isPresent().hasValue(HttpLoggingType.FULL);
        assertThat(resultBasic).isPresent().hasValue(HttpLoggingType.BASIC);
    }

    @Test
    void WhenCalled_GivenInvalidString_Expect_EmptyOptional() {
        Optional<HttpLoggingType> result = HttpLoggingType.from(INVALID);

        assertThat(result).isEmpty();
    }

    @Test
    void  WhenCalled_GivenEmptyString_Expect_EmptyOptional() {
        Optional<HttpLoggingType> result = HttpLoggingType.from(EMPTY);

        assertThat(result).isEmpty();
    }

    @Test
    void WhenCalled_GivenNullString_Expect_EmptyOptional() {
        Optional<HttpLoggingType> result = HttpLoggingType.from(null);

        assertThat(result).isEmpty();
    }

}
