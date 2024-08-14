package ru.koryruno.springbootstartert1.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import ru.koryruno.springbootstartert1.annotation.ConditionalOnHttpLoggingFilterCondition;
import ru.koryruno.springbootstartert1.aspect.TracingLoggingAspect;
import ru.koryruno.springbootstartert1.filter.BasicHttpLoggingFilter;
import ru.koryruno.springbootstartert1.filter.FullHttpLoggingFilter;

/**
 * Autoconfiguration for setting up logging in the application.
 *
 * <p>This class configures logging based on the properties defined in {@link LoggingProperties} and
 * provides beans for basic and full HTTP request logging, as well as method tracing.</p>
 *
 * <h3>Example:</h3>
 * <pre>
 * {@code
 * // Add to application property settings:
 * kory.logging.starter.enable=true
 * kory.logging.starter.http-logging-enable=true
 * kory.logging.starter.http-logging-type=full
 * kory.logging.starter.method-tracing-enable=true
 * }
 * </pre>
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class)
@ConditionalOnProperty(prefix = "kory.logging.starter", value = "enable", havingValue = "true")
public class LoggingAutoConfiguration {
    private final LoggingProperties properties;

    public LoggingAutoConfiguration(LoggingProperties properties) {
        this.properties = properties;
    }

    @PostConstruct
    public void init() {
        log.info("HttpLoggingAutoConfiguration initialized with properties: {}", properties);
    }

    /**
     * Create Bean for basic http requests logging
     * @return Bean {@link BasicHttpLoggingFilter}
     */
    @Bean
    @Order(1)
    @ConditionalOnMissingBean
    @ConditionalOnHttpLoggingFilterCondition
    @ConditionalOnProperty(prefix = "kory.logging.starter", value = "http-logging-type",
            havingValue = "basic", matchIfMissing = true)
    public BasicHttpLoggingFilter basicLoggingFilter() {
        return new BasicHttpLoggingFilter();
    }

    /**
     * Create Bean for full http requests logging
     * @return Bean {@link FullHttpLoggingFilter}
     */
    @Bean
    @Order(1)
    @ConditionalOnMissingBean
    @ConditionalOnHttpLoggingFilterCondition
    @ConditionalOnProperty(prefix = "kory.logging.starter", value = "http-logging-type",
            havingValue = "full", matchIfMissing = false)
    public FullHttpLoggingFilter fullLoggingFilter() {
        return new FullHttpLoggingFilter();
    }

    /**
     * Create Bean for tracing methods logging
     * @return Bean {@link TracingLoggingAspect}
     */
    @Bean
    @Order(1)
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "kory.logging.starter", value = "method-tracing-enable",
            havingValue = "true", matchIfMissing = false)
    public TracingLoggingAspect tracingLoggingAspect(Environment environment) {
        return new TracingLoggingAspect(environment);
    }

}
