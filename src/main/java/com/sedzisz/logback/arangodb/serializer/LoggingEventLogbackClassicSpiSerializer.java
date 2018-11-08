package com.sedzisz.logback.arangodb.serializer;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackSerializationContext;
import com.arangodb.velocypack.VPackSerializer;
import com.arangodb.velocypack.exception.VPackException;

public class LoggingEventLogbackClassicSpiSerializer implements VPackSerializer<LoggingEvent> {

    @Override
    public void serialize(VPackBuilder vPackBuilder, String s, LoggingEvent loggingEvent, VPackSerializationContext vPackSerializationContext) throws VPackException {
        vPackBuilder
                .add("threadName", loggingEvent.getThreadName())
                .add("LoggerName", loggingEvent.getLoggerName())
                .close();
    }
}
