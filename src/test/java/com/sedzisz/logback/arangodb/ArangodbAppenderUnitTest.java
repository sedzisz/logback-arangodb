package com.sedzisz.logback.arangodb;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ArangodbAppenderUnitTest {

    @Test
    void append() {
        // given

        // when
        log.info("Dupa");
        log.error("ERROR ", new RuntimeException("Example exception!"));

        // then
        log.info("End");
    }

    @Test
    void start() {
        // given

        // when

        // then
    }

    @Test
    void stop() {
        // given

        // when

        // then
    }
}