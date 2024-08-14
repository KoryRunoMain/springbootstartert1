package ru.koryruno.springbootstartert1.annotation;

import org.springframework.context.annotation.Conditional;
import ru.koryruno.springbootstartert1.filter.ConditionalOnHttpLoggingFilter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for conditional loading of components depending on the presence of HTTP logging.
 *
 * <p>This annotation can be applied to any class or method to conditionally create a bean
 * only if the conditions defined in {@link ConditionalOnHttpLoggingFilter} are met.
 * The annotation checks whether HTTP logging is enabled in the application
 * and loads the appropriate components if necessary.</p>
 *
 * <h3>Example:</h3>
 * <pre>
 * {@code
 * @Configuration
 * public class MyConfig {
 *
 *     @Bean
 *     @ConditionalOnHttpLoggingFilterCondition
 *     public MyLoggingFilter myLoggingFilter() {
 *         return new MyLoggingFilter();
 *     }
 * }
 * }
 * </pre>
 *
 * @see ConditionalOnHttpLoggingFilter
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(ConditionalOnHttpLoggingFilter.class)
public @interface ConditionalOnHttpLoggingFilterCondition {
}
