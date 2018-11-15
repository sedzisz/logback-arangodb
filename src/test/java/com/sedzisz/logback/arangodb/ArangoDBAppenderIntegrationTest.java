package com.sedzisz.logback.arangodb;

import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
@Slf4j
public class ArangoDBAppenderIntegrationTest {

    @Test
    public void shouldLogInfo() {
        // given

        // when
        log.info("Info logs without arguments.");
        log.info("Info logs with arguments: {}", "[arguments]");
        log.info("Info logs with arguments: {} with exception.", "[arguments]", new RuntimeException("Message from exception - Info."));
        log.info("Info logs without arguments with exception.", new RuntimeException("Message from exception - Info."));

        // then
    }

    @Test
    public void shouldLogWarn() {
        // given

        // when
        log.warn("Warn logs without arguments.");
        log.warn("Warn logs with arguments: {}", "[arguments]");
        log.warn("Warn logs with arguments: {} with exception.", "[arguments]", new RuntimeException("Message from exception - Warn."));
        log.warn("Warn logs without arguments with exception.", new RuntimeException("Message from exception - Warn."));

        // then

    }

    @Test
    public void shouldLogError() {
        // given

        // when
        log.error("Error logs without arguments.");
        log.error("Error logs with arguments: {}", "[arguments]");
        log.error("Error logs with arguments: {} with exception.", "[arguments]", new RuntimeException("Message from exception - Error."));
        log.error("Error logs without arguments with exception.", new RuntimeException("Message from exception - Error."));

        // then

    }

    @Test
    public void shouldLogDebug() {
        // given

        // when
        log.debug("Debug logs without arguments.");
        log.error("Debug logs with arguments: {}", "[arguments]");
        log.error("Debug logs with arguments: {} with exception.", "[arguments]", new RuntimeException("Message from exception - Debug."));
        log.error("Debug logs without arguments with exception.", new RuntimeException("Message from exception - Debug."));

        // then

    }
}