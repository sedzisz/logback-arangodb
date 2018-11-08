package com.sedzisz.logback.arangodb.serializer;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.sedzisz.logback.arangodb.LoggingEventArangoDb;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArangoDbConverter {

    public LoggingEventArangoDb append(LoggingEvent logEvent) {
        return new LoggingEventArangoDb(logEvent);
    }
}
