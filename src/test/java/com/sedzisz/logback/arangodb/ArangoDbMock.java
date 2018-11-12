package com.sedzisz.logback.arangodb;

import ch.qos.logback.core.Appender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Slf4j
@RunWith(MockitoJUnitRunner.Silent.class)
public class ArangoDbMock {

    @InjectMocks
    ArangoDBAppender arangoDBAppender = new ArangoDBAppender();

    @Mock
    AranoDBConnection aranoDBConnection;


    ch.qos.logback.classic.Logger logger;

    @Before
    public void setup() {
        logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);


        /*given(aranoDBConnection.append(any()));*/
        //given(appender.isStarted()).willReturn(true);
        //logger.addAppender(appender);
    }

    @Test
    public void testValidate() {
     /*   HeaderValidator validator = new HeaderValidator();
        validator.validate("ABC");

        verify(appender, times(1)).doAppend(argThat(new ArgumentMatcher() {
            @Override
            public boolean matches(Object argument) {
                return ((ILoggingEvent) argument).getFormattedMessage().equals("E2011 Invalid Header Content");
            }
        }));
*/
        log.info("Dupa");

    }
}
