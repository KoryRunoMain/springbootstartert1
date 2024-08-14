package ru.koryruno.springbootstartert1.aspectTest;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import ru.koryruno.springbootstartert1.annotation.Loggable;
import ru.koryruno.springbootstartert1.aspect.TracingLoggingAspect;
import ru.koryruno.springbootstartert1.exception.LoggingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class TracingLoggingAspectTest {

    @Mock
    private Environment environment;
    @Mock
    private ProceedingJoinPoint joinPoint;
    @InjectMocks
    private TracingLoggingAspect tracingLoggingAspect;

    // Keys
    private static final String BASE_KEY = "kory.logging.starter.method-tracing-enable";
    private static final String ENABLE = "true";
    private static final String DISABLE = "false";
    private static final String RESULT = "result";
    private static final String THROW_MESSAGE = "Throw message";

    @BeforeEach
    void setUp() {
        lenient().when(joinPoint.getSignature()).thenReturn(mock(Signature.class));
    }

    @Test
    void When_TracingLoggingAspectIsEnabled_Expect_Successfully() throws Throwable {
        when(environment.getProperty(BASE_KEY, DISABLE)).thenReturn(ENABLE);
        when(joinPoint.proceed()).thenReturn(RESULT);
        when(joinPoint.getTarget()).thenReturn(new Object());

        tracingLoggingAspect.logBasicAspect(joinPoint, mock(Loggable.class));

        verify(joinPoint).proceed();
    }

    @Test
    void When_TracingLoggingAspectIsDisabled_Expect_DoesNotLogging() throws Throwable {
        when(environment.getProperty(BASE_KEY, DISABLE)).thenReturn(DISABLE);
        when(joinPoint.proceed()).thenReturn(RESULT);

        Object result = tracingLoggingAspect.logBasicAspect(joinPoint, null);

        verify(joinPoint).proceed();
        assertEquals(RESULT, result);
    }

    @Test
    void When_TracingLoggingAspectIsEnable_Expect_Throws() throws Throwable {
        when(environment.getProperty(BASE_KEY, DISABLE)).thenReturn(ENABLE);
        when(joinPoint.proceed()).thenThrow(new RuntimeException(THROW_MESSAGE));
        when(joinPoint.getTarget()).thenReturn(new Object());

        try {
            tracingLoggingAspect.logBasicAspect(joinPoint, mock(Loggable.class));
        } catch (LoggingException e) {
            verify(joinPoint).proceed();
            assertTrue(e.getMessage().contains(THROW_MESSAGE));
        }
    }

}
