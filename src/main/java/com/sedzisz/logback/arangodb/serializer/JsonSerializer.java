package com.sedzisz.logback.arangodb.serializer;

import ch.qos.logback.classic.spi.LoggingEvent;
import com.arangodb.ArangoDBException;
import com.arangodb.util.ArangoSerialization;
import com.arangodb.velocypack.VPackSlice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

@Slf4j
public class JsonSerializer  implements ArangoSerialization {

    private final ObjectMapper mapper = new ObjectMapper();

    private String convert(LoggingEvent logEvent) {
        try {
            return mapper.writeValueAsString(logEvent);
        } catch (JsonProcessingException e) {
            log.error("", e);
            return null;
        }
    }

    @Override
    public <T> T deserialize(VPackSlice vpack, Type type) throws ArangoDBException {
        return null;
    }

    @Override
    public VPackSlice serialize(Object entity) throws ArangoDBException {
        return null;
    }

    @Override
    public VPackSlice serialize(Object entity, Options options) throws ArangoDBException {
        return null;
    }
}
