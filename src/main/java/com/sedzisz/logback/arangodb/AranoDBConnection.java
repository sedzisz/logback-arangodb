package com.sedzisz.logback.arangodb;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import com.arangodb.ArangoCollection;
import com.arangodb.ArangoDB;
import com.arangodb.entity.BaseDocument;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static java.util.Collections.EMPTY_LIST;

@NoArgsConstructor(access = AccessLevel.NONE)
class AranoDBConnection<E> {

    private ArangoCollection arangoCollection;
    private ArangoDBAppender context;

    @Builder
    private AranoDBConnection(ArangoDBAppender context, String host, int port, String user, String password, String database, String collection) {
        this.context = context;
        final ArangoDB arango = new ArangoDB.Builder()
                .host(host, port).user(user).password(password).build();
        if (arango.db(database).exists()) {
            context.addInfo(String.format("ArangoDb appender will user database: [%s]", database));
            if (arango.db(database).collection(collection).exists()) {
                context.addInfo(String.format("ArangoDb appender will user collection: [%s]", collection));
                arangoCollection = arango.db(database).collection(collection);
            } else {
                final String error = String.format("Collection: [%s] don't exist.", collection);
                context.addError(error, new IllegalStateException(error));
            }
        } else {
            final String error = String.format("Database: [%s] don't exist.", database);
            context.addError(error, new IllegalStateException(error));
        }
    }

    public void stop() {
        context.addInfo("Shutdown ArangoDb connection.");
        arangoCollection.db().arango().shutdown();
    }

    public void append(E logEvent) {
        arangoCollection.insertDocument(convert(logEvent));
    }

    private BaseDocument convert(E loggingEvent) {
        ILoggingEvent eventObject = (ILoggingEvent) loggingEvent;
        final BaseDocument document = new BaseDocument();
        document.addAttribute("level", eventObject.getLevel().toString());
        document.addAttribute("message", eventObject.getMessage());
        document.addAttribute("formattedMessage", eventObject.getFormattedMessage());
        document.addAttribute("callerData", eventObject.getCallerData() != null ? eventObject.getCallerData() : EMPTY_LIST);
        document.addAttribute("argumentArray", eventObject.getArgumentArray() != null ? eventObject.getArgumentArray() : EMPTY_LIST);
        document.addAttribute("loggerName", eventObject.getLoggerName());
        document.addAttribute("threadName", eventObject.getThreadName());
        document.addAttribute("timeStamp", eventObject.getTimeStamp());
        document.addAttribute("stackTrace", eventObject.getThrowableProxy() != null ? new IThrowableProxyString(eventObject.getThrowableProxy()) : EMPTY_LIST);
        return document;
    }

    @Getter
    private class IThrowableProxyString {

        public String message;
        public String className;
        public StackTraceElementProxy[] stackTraceElementProxyArray;
        public int commonFrames;
        public IThrowableProxy[] suppressed;

        protected IThrowableProxyString(IThrowableProxy throwableProxy) {
            message = throwableProxy.getMessage();
            className = throwableProxy.getClassName();
            commonFrames = throwableProxy.getCommonFrames();
            stackTraceElementProxyArray = throwableProxy.getStackTraceElementProxyArray();
        }
    }
}
