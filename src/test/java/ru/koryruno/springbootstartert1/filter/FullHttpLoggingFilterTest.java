package ru.koryruno.springbootstartert1.filter;

import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = FullHttpLoggingFilter.class)
public class FullHttpLoggingFilterTest {

    BasicHttpLoggingFilter filter;
    MockHttpServletRequest request;
    MockHttpServletResponse response;
    MockFilterChain chain;

    @BeforeEach
    void init() {
        filter = new BasicHttpLoggingFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        chain = new MockFilterChain();
    }

    @Test
    void FullHttpLogging_WithFullParam_Expect_Successfully() throws IOException, ServletException {
        request.setMethod("POST");
        request.setRequestURI("/test");
        request.addHeader("Content-Type", "application/json");

        filter.doFilter(request, response, chain);

        assertTrue(response.getContentAsString().isEmpty());
        assertEquals(200, response.getStatus());
    }

    @Test
    void FullHttpLogging_WithoutURI_Expect_Successfully() throws IOException, ServletException {
        request.setMethod("POST");
        request.addHeader("Content-Type", "application/json");

        filter.doFilter(request, response, chain);

        assertTrue(response.getContentAsString().isEmpty());
        assertEquals(200, response.getStatus());
    }

    @Test
    void FullHttpLogging_WithMissingParams_Expect_Successfully() throws IOException, ServletException {
        filter.doFilter(request, response, chain);

        assertTrue(response.getContentAsString().isEmpty());
        assertEquals(200, response.getStatus());
    }

}
