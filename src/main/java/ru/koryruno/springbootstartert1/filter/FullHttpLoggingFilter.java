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
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Filter for full logging of HTTP requests and responses.
 *
 * <p>This filter logs all available information about each HTTP request and response,
 * including the request method, URI, request parameters, request headers, response status, response headers,
 * and the duration of the request.</p>
 *
 * <p>Used for detailed monitoring of HTTP traffic, which can be useful for diagnosing and
 * debugging networking issues in your application.</p>
 */
@Slf4j
public class FullHttpLoggingFilter implements Filter {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("BasicHttpLoggingFilter initialized {}", this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        LocalDateTime startRequestTime = LocalDateTime.now();
        log.info("Request: method={}, uri={}, query={}, headers={}, startTime={}",
                httpRequest.getMethod(),
                httpRequest.getRequestURI(),
                httpRequest.getQueryString(),
                getHeaders(httpRequest),
                startRequestTime.format(FORMATTER));

        chain.doFilter(request, response);

        LocalDateTime endRequestTime = LocalDateTime.now();
        long duration = ChronoUnit.MILLIS.between(startRequestTime, endRequestTime);

        log.info("Response: status={}, headers={}, endTime={}, duration={} ms",
                httpResponse.getStatus(),
                getResponseHeaders(httpResponse),
                endRequestTime.format(FORMATTER),
                duration);
    }

    private String getHeaders(HttpServletRequest httpRequest) {
        return Collections.list(httpRequest.getHeaderNames()).stream()
                .map(name -> name + "=" + httpRequest.getHeader(name))
                .collect(Collectors.joining(", "));
    }

    private String getResponseHeaders(HttpServletResponse httpResponse) {
        return httpResponse.getHeaderNames().stream()
                .map(name -> name + "=" + httpResponse.getHeader(name))
                .collect(Collectors.joining(", "));
    }

    @Override
    public void destroy() {
        log.info("BasicHttpLoggingFilter destroyed {}", this);
    }

}
