package com.sedzisz.logback.arangodb.serializer;

import com.arangodb.velocypack.VPackBuilder;
import com.arangodb.velocypack.VPackSerializationContext;
import com.arangodb.velocypack.VPackSerializer;
import com.arangodb.velocypack.exception.VPackException;
import org.apache.log4j.spi.LoggingEvent;

public class LoggingEventLog4jSerializer implements VPackSerializer<LoggingEvent> {

    @Override
    public void serialize(VPackBuilder vPackBuilder, String s, LoggingEvent loggingEvent, VPackSerializationContext vPackSerializationContext) throws VPackException {
        vPackBuilder.close();
    }
}
