package com.sedzisz.logback.arangodb.serializer;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackJsonSerializer;
import com.arangodb.velocypack.exception.VPackException;

public class LoggingEventLogbackClassicSpiJsonSerializer implements VPackJsonSerializer<LoggingEvent> {
    @Override
    public void serialize(VPackBuilder vPackBuilder, String source, LoggingEvent loggingEvent) throws VPackException {
        vPackBuilder
                .add("threadName", loggingEvent.getThreadName())
                .add("LoggerName", loggingEvent.getLoggerName())
                .close();
    }
}
