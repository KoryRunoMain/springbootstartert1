package ru.koryruno.springbootstartert1.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogLevel;

/**
 * Properties
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "kory.logging.starter")
public class LoggingProperties {

    /**
     * Enable to activate "Logging Starter"
     * property: kory.logging.starter.enable=true
     */
    private boolean enable;

    /**
     * Enable to activate "Method Tracing"
     * When property is enabled use annotation @Loggable on any class or method
     * property: kory.logging.starter.method-tracing-enable=true
     */
    private boolean methodTracingEnable;

    /**
     * Enable to activate "Http Logging"
     * property: kory.logging.starter.http-logging-enable=true
     */
    private boolean httpLoggingEnable;

    /**
     * Change type of "Http Logging"
     * Possible type properties are: "BASIC" short info, "FULL" full info
     * Parameters are not case-sensitive
     * property: kory.logging.starter.http-logging-type=full
     */
    private String httpLoggingType;

    /**
     * Defines the log level for the "logging-starter"
     * default type: LogLevel.INFO
     * Possible values: INFO, DEBUG, WARN, ERROR, etc.
     * You can use it with @Loggable. Provide the value in brackets
     * example: @Loggable(level = LogLevel.ERROR)
     */
    private LogLevel logLevel;

    @PostConstruct
    public void init() {
        log.info("Logging properties initialized: enable={}, " +
                        "methodTracingEnable={}, " +
                        "httpLoggingEnable={}, " +
                        "httpLoggingType={}, " +
                        "logLevel={}",
                enable, methodTracingEnable, httpLoggingEnable, httpLoggingType,  logLevel);
    }

}
