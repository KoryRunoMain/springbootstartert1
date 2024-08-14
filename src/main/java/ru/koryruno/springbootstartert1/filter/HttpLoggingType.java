package ru.koryruno.springbootstartert1.filter;

import java.util.Optional;

public enum HttpLoggingType {
    FULL, BASIC;

    public static Optional<HttpLoggingType> from(String stringTypeAction) {
        for (HttpLoggingType state : values()) {
            if (state.name().equalsIgnoreCase(stringTypeAction)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }

}
