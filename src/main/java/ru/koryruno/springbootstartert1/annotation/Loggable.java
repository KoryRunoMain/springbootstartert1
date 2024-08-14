package ru.koryruno.springbootstartert1.annotation;

import org.springframework.boot.logging.LogLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the logging level for the annotated method or class.
 *
 * <p>This annotation can be applied to any class or method to automatically enable logging at the specified level.
 * It provides a flexible way to customize the logging level.</p>
 *
 * <h3>Example:</h3>
 * <pre>
 * {@code
 * @Loggable(level = LogLevel.INFO)
 * public class MyService {
 *
 *     @Loggable(level = LogLevel.ERROR)
 *     public void createAction() {
 *         // Some code here..
 *     }
 * }
 * }
 * </pre>
 *
 * <p>In this example, the `MyService` class will log at INFO level,
 * and the `performAction` method will log at ERROR level.</p>
 *
 * @see LogLevel
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Loggable {

    /**
     * The log level for the annotated method or class.
     */
    LogLevel level() default LogLevel.INFO;

}
