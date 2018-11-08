package com.sedzisz.logback.arangodb;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.LoggerContextVO;
import ch.qos.logback.classic.spi.LoggingEvent;
import lombok.Data;
import org.slf4j.Marker;

import java.util.Map;

@Data
public class LoggingEventArangoDb {

    private String threadName;

    private String loggerName;

    private transient LoggerContext loggerContext;

    private transient LoggerContextVO loggerContextVO;

    private transient Level level;

    private String message;

    private transient String formattedMessage;

    private transient Object[] argumentArray;

    private IThrowableProxy throwableProxy;

    private StackTraceElement[] callerDataArray;

    private Marker marker;

    private Map<String, String> mdcPropertyMap;

    private long timeStamp;

    public LoggingEventArangoDb(LoggingEvent loggingEvent) {
        this.threadName = loggingEvent.getThreadName();
        this.loggerName = loggingEvent.getLoggerName();
        this.level = loggingEvent.getLevel();
        this.message = loggingEvent.getMessage();
        this.throwableProxy = loggingEvent.getThrowableProxy();
        this.callerDataArray = loggingEvent.getCallerData();
        this.marker = loggingEvent.getMarker();
        this.timeStamp = loggingEvent.getTimeStamp();
    }
}
