package com.sedzisz.logback.arangodb.serializer;

import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackJsonSerializer;
import com.arangodb.velocypack.exception.VPackException;
import org.slf4j.event.LoggingEvent;

public class LoggingEventSlf4jJsonSerializer implements VPackJsonSerializer<LoggingEvent> {
    @Override
    public void serialize(VPackBuilder vPackBuilder, String source, LoggingEvent loggingEvent) throws VPackException {
        vPackBuilder
                .add("threadName", loggingEvent.getThreadName())
                .add("LoggerName", loggingEvent.getLoggerName())
                .close();
    }
}
