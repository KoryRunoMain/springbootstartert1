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

@SpringBootTest(classes = BasicHttpLoggingFilter.class)
public class BasicHttpLoggingFilterTest {

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
    public void BasicHttpLogging_WithBasicParams_Expect_Successfully() throws IOException, ServletException {
        request.setMethod("GET");
        request.setRequestURI("/test");

        filter.doFilter(request, response, chain);

        assertTrue(response.getContentAsString().isEmpty());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void BasicHttpLogging_WithMissingParams_Expect_Successfully() throws IOException, ServletException {
        filter.doFilter(request, response, chain);

        assertTrue(response.getContentAsString().isEmpty());
        assertEquals(200, response.getStatus());
    }

    @Test
    public void BasicHttpLogging_WithoutURI_Expect_Successfully() throws IOException, ServletException {
        request.setMethod("POST");

        filter.doFilter(request, response, chain);

        assertTrue(response.getContentAsString().isEmpty());
        assertEquals(200, response.getStatus());
    }

}
