package com.sedzisz.logback.arangodb.serializer;

import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackSerializationContext;
import com.arangodb.velocypack.VPackSerializer;
import com.arangodb.velocypack.exception.VPackException;
import org.slf4j.event.LoggingEvent;

public class LoggingEventSlf4jSerializer implements VPackSerializer<LoggingEvent> {

    @Override
    public void serialize(VPackBuilder vPackBuilder, String s, LoggingEvent loggingEvent, VPackSerializationContext vPackSerializationContext) throws VPackException {
        vPackBuilder
                .add("threadName", loggingEvent.getThreadName())
                .add("LoggerName", loggingEvent.getLoggerName())
                .close();
    }
}
