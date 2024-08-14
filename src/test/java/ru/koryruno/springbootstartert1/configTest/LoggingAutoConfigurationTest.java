package ru.koryruno.springbootstartert1.configTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import ru.koryruno.springbootstartert1.aspect.TracingLoggingAspect;
import ru.koryruno.springbootstartert1.config.LoggingAutoConfiguration;
import ru.koryruno.springbootstartert1.filter.BasicHttpLoggingFilter;
import ru.koryruno.springbootstartert1.filter.FullHttpLoggingFilter;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LoggingAutoConfiguration.class)
public class LoggingAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(LoggingAutoConfiguration.class));

    // Keys
    private static final String BASE_KEY = "kory.logging.starter.enable=";
    private static final String METHOD_TRACING_KEY = "kory.logging.starter.method-tracing-enable=";
    private static final String BASE_KEY_HTTP_LOG = "kory.logging.starter.http-logging-enable=";
    private static final String BASE_KEY_HTTP_LOG_TYPE = "kory.logging.starter.http-logging-type=";
    private static final String ENABLE = "TRUE";
    private static final String DISABLE = "FALSE";
    private static final String BASIC = "BASIC";
    private static final String FULL = "FULL";

    @Test
    public void When_BasicLoggingFilterIsEnabled_And_LoggingTypeIsMissing_Expect_IsRegistered_ByDefault() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                BASE_KEY_HTTP_LOG + ENABLE
        ).run(context -> {
            assertThat(context).hasSingleBean(BasicHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(FullHttpLoggingFilter.class);
        });
    }

    @Test
    public void When_BasicLoggingFilterIsEnabled_WithTypeInUpperCase_Expect_IsRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                BASE_KEY_HTTP_LOG + ENABLE,
                BASE_KEY_HTTP_LOG_TYPE + BASIC
        ).run(context -> {
            assertThat(context).hasSingleBean(BasicHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(FullHttpLoggingFilter.class);
        });
    }

    @Test
    public void When_BasicLoggingFilterIsEnabled_WithTypeInLowerCase_Expect_IsRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                BASE_KEY_HTTP_LOG + ENABLE,
                BASE_KEY_HTTP_LOG_TYPE + BASIC.toLowerCase()
        ).run(context -> {
            assertThat(context).hasSingleBean(BasicHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(FullHttpLoggingFilter.class);
        });
    }

    @Test
    public void When_FullLoggingFilterIsEnabled_WithTypeInUpperCase_Expect_IsRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                BASE_KEY_HTTP_LOG + ENABLE,
                BASE_KEY_HTTP_LOG_TYPE + FULL
        ).run(context -> {
            assertThat(context).hasSingleBean(FullHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(BasicHttpLoggingFilter.class);
        });
    }

    @Test
    public void When_FullLoggingFilterIsEnabled_WithTypeInLowerCase_Expect_IsRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                BASE_KEY_HTTP_LOG + ENABLE,
                BASE_KEY_HTTP_LOG_TYPE + FULL.toLowerCase()
        ).run(context -> {
            assertThat(context).hasSingleBean(FullHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(BasicHttpLoggingFilter.class);
        });
    }

    @Test
    public void When_StarterIsDisabled_And_HttpLoggingIsEnabled_Expect_NoBeanRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + DISABLE,
                BASE_KEY_HTTP_LOG + ENABLE
        ).run(context -> {
            assertThat(context).doesNotHaveBean(BasicHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(FullHttpLoggingFilter.class);
        });
    }

    @Test
    public void When_StarterIsDisabled_And_HttpLoggingIsDisabled_Expect_NoBeanRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + DISABLE,
                BASE_KEY_HTTP_LOG + DISABLE
        ).run(context -> {
            assertThat(context).doesNotHaveBean(BasicHttpLoggingFilter.class);
            assertThat(context).doesNotHaveBean(FullHttpLoggingFilter.class);
        });
    }

    @Test
    void When_TracingAspectIsEnabled_Expect_IsRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                METHOD_TRACING_KEY + ENABLE
        ).run(context -> {
            assertThat(context).hasSingleBean(TracingLoggingAspect.class);
        });
    }

    @Test
    void WhenTracingAspectIsDisabled_Expect_NoBeanRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + ENABLE,
                METHOD_TRACING_KEY + DISABLE
        ).run(context -> {
            assertThat(context).doesNotHaveBean(TracingLoggingAspect.class);
        });
    }

    @Test
    void When_StarterIsDisabled_And_TracingAspectIsEnabled_Expect_NoBeanRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + DISABLE,
                METHOD_TRACING_KEY + ENABLE
        ).run(context -> {
            assertThat(context).doesNotHaveBean(TracingLoggingAspect.class);
        });
    }

    @Test
    void When_StarterIsDisabled_And_TracingAspectIsDisabled_Expect_NoBeanRegistered() {
        contextRunner.withPropertyValues(
                BASE_KEY + DISABLE,
                METHOD_TRACING_KEY + DISABLE
        ).run(context -> {
            assertThat(context).doesNotHaveBean(TracingLoggingAspect.class);
        });
    }

}
