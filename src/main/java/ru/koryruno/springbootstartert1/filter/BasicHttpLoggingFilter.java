package ru.koryruno.springbootstartert1.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Filter for basic logging of HTTP requests and responses.
 *
 * <p>This filter logs basic information about each HTTP request and response,
 * including the request method, URI, request start and end time, response status and duration.</p>
 *
 * <p>It is useful for tracking requests and responses in an application,
 * helping with monitoring and debugging.</p>
 */
@Slf4j
public class BasicHttpLoggingFilter implements Filter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("FullHttpLoggingFilter initialized {}", this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        LocalDateTime startTime = LocalDateTime.now();
        log.info("Request: method={}, uri={}, start time request={}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                startTime.format(FORMATTER));

        chain.doFilter(request, response);

        LocalDateTime endTime = LocalDateTime.now();
        long duration = ChronoUnit.MILLIS.between(startTime, endTime);

        log.info("Response: status={}, end time request={}, duration={}",
                httpResponse.getStatus(),
                endTime.format(FORMATTER),
                duration + "ms");
    }

    @Override
    public void destroy() {
        log.info("FullHttpLoggingFilter destroyed {}", this);
    }

}
