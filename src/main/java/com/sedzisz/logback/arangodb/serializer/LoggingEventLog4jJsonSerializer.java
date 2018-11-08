package com.sedzisz.logback.arangodb.serializer;

import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackJsonSerializer;
import com.arangodb.velocypack.exception.VPackException;
import org.apache.log4j.spi.LoggingEvent;

public class LoggingEventLog4jJsonSerializer implements VPackJsonSerializer<LoggingEvent> {
    @Override
    public void serialize(VPackBuilder vPackBuilder, String source, LoggingEvent loggingEvent) throws VPackException {
        vPackBuilder.close();
    }
}
