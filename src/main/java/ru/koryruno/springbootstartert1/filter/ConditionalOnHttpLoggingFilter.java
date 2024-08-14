package ru.koryruno.springbootstartert1.filter;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

/**
 * Condition to activate bean based on http-logging properties.
 *
 * <p>This class implements a condition for Spring that checks the presence and
 * value of `kory.logging.starter.enable` and `kory.logging.starter.http-logging-enable` properties
 * in the environment.</p>
 *
 * <h3>Example:</h3>
 * <pre>
 * {@code
 * @Conditional(ConditionalOnHttpLoggingFilter.class)
 * public class MyHttpLoggingFilter {
 *     // Bean definition
 * }
 * }
 * </pre>
 */
public class ConditionalOnHttpLoggingFilter implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var enabled = Optional.ofNullable(context.getEnvironment().getProperty("kory.logging.starter.enable"));
        var httpLoggingEnable = Optional.ofNullable(context.getEnvironment().getProperty("kory.logging.starter.http-logging-enable"));
        boolean hasProps = enabled.isPresent() && httpLoggingEnable.isPresent();

        return hasProps && Boolean.parseBoolean(httpLoggingEnable.get());
    }

}
