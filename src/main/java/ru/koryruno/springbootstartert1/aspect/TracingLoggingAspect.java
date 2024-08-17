package ru.koryruno.springbootstartert1.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.env.Environment;
import ru.koryruno.springbootstartert1.annotation.Loggable;
import ru.koryruno.springbootstartert1.exception.LoggingException;
import ru.koryruno.springbootstartert1.filter.LogConsoleFilter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Aspect for logging method calls annotated with {@link Loggable}.
 *
 * <p>This aspect is responsible for tracking the execution time of methods and logging their calls, results,
 * and exceptions if they occur. It uses the {@link Loggable}
 * annotation to determine which methods or classes should be tracked.</p>
 *
 * <h3>Example:</h3>
 * <pre>
 * {@code
 * @Loggable(level = LogLevel.INFO)
 * public void myMethod() {
 *     // Some code here..
 * }
 * }
 * </pre>
 *
 * @see Loggable
 * @see LoggingException
 * @see LogConsoleFilter
 */
@Aspect
@Slf4j
public class TracingLoggingAspect {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final Environment environment;

    public TracingLoggingAspect(Environment environment) {
        this.environment = environment;
    }

    @Pointcut("@annotation(loggable) || @within(loggable)")
    public void useLoggableAspect(Loggable loggable) {
    }

    @Around("useLoggableAspect(loggable)")
    public Object logBasicAspect(ProceedingJoinPoint proceedingJoinPoint, Loggable loggable) throws Throwable {

        String loggingEnabled = environment.getProperty("kory.logging.starter.method-tracing-enable", "false");
        if (!Boolean.parseBoolean(loggingEnabled) && loggable == null) {
            return proceedingJoinPoint.proceed();
        }

        LocalDateTime startRequestTime = LocalDateTime.now();
        String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String methodName = proceedingJoinPoint.getSignature().getName();

        log.info("Request: {}.{}() at {}", className, methodName, startRequestTime.format(FORMATTER));

        Object result;
        long duration;

        try {
            result = proceedingJoinPoint.proceed();
            LocalDateTime endRequestTime = LocalDateTime.now();
            duration = ChronoUnit.MILLIS.between(startRequestTime, endRequestTime);
            log.info("Response: {}.{}() with result {} in {}ms at {}", className, methodName,
                    LogConsoleFilter.truncateResult(result), duration, endRequestTime.format(FORMATTER));

        } catch (Throwable e) {
            LocalDateTime errorRequestTime = LocalDateTime.now();
            duration = ChronoUnit.MILLIS.between(startRequestTime, errorRequestTime);
            log.error("Exception: {} in {}ms at {}", e.getMessage(), duration, errorRequestTime.format(FORMATTER));
            throw new LoggingException(e.getMessage(), duration, errorRequestTime.format(FORMATTER));
        }

        return result;
    }

}
