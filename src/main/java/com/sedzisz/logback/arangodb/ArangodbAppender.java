package com.sedzisz.logback.arangodb;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Context;
import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCollectionAsync;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBAsync;
import com.arangodb.entity.BaseDocument;
import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;
import com.sedzisz.logback.arangodb.serializer.*;

/**
 * @author Zdzisław Sędek <zdzislaw.sedek@gmail.com>
 */
public class ArangodbAppender<E> extends AppenderBase<E> {

    private String facility = "ARANGO_DB";
    private String host = "localhost";
    private int port = 8529;
    private String database = "logback_db";
    private String collection = "logs";
    private boolean async;
    private String user;
    private String password;

    // Private fields
    private ArangoDbConnection arangoDbConnection;

    @Override
    protected void append(E logEvent) {
        arangoDbConnection.append(logEvent);
    }

    @Override
    public void start() {
        super.start();
        arangoDbConnection = new ArangoDbConnection(context);
    }

    @Override
    public void stop() {
        super.stop();
        arangoDbConnection.stop();
    }

    private class ArangoDbConnection {

        private ArangoCollection arangodb;
        private ArangoCollectionAsync arangoDBAsync;
        private Context context;

        public ArangoDbConnection(Context context) {
            this.context = context;
            if (async) {
                System.out.println("Initialize ArangoDb appender in ASYNC-MODE");

                arangoDBAsync = new ArangoDBAsync.Builder()
                        .host(host, port).user(user).password(password)
                        .registerModules(new VPackJdk8Module())
                        // Object
                        .registerSerializer(ch.qos.logback.classic.spi.LoggingEvent.class, new LoggingEventLogbackClassicSpiSerializer())
                        .registerSerializer(org.slf4j.event.LoggingEvent.class, new LoggingEventSlf4jSerializer())
                        .registerSerializer(org.apache.log4j.spi.LoggingEvent.class, new LoggingEventLog4jSerializer())
                        // JSON
                        .registerJsonSerializer(ch.qos.logback.classic.spi.LoggingEvent.class, new LoggingEventLogbackClassicSpiJsonSerializer())
                        .registerJsonSerializer(org.slf4j.event.LoggingEvent.class, new LoggingEventSlf4jJsonSerializer())
                        .registerJsonSerializer(org.apache.log4j.spi.LoggingEvent.class, new LoggingEventLog4jJsonSerializer())
                        .build()
                        .db(database).collection(collection);

            } else {
                arangodb = new ArangoDB.Builder()
                        .host(host, port).user(user).password(password)
                        .registerModules(new VPackJdk8Module())
                        // Object
                        .registerSerializer(ch.qos.logback.classic.spi.LoggingEvent.class, new LoggingEventLogbackClassicSpiSerializer())
                        .registerSerializer(org.slf4j.event.LoggingEvent.class, new LoggingEventSlf4jSerializer())
                        .registerSerializer(org.apache.log4j.spi.LoggingEvent.class, new LoggingEventLog4jSerializer())
                        // JSON
                        .registerJsonSerializer(ch.qos.logback.classic.spi.LoggingEvent.class, new LoggingEventLogbackClassicSpiJsonSerializer())
                        .registerJsonSerializer(org.slf4j.event.LoggingEvent.class, new LoggingEventSlf4jJsonSerializer())
                        .registerJsonSerializer(org.apache.log4j.spi.LoggingEvent.class, new LoggingEventLog4jJsonSerializer())
                        .build()
                        .db(database).collection(collection);
            }
        }

        public void stop() {
            addInfo("Shutdown ArangoDb connection.");
            if (arangoDBAsync != null) {
                arangoDBAsync.db().arango().shutdown();
            } else {
                arangodb.db().arango().shutdown();
            }
        }

        public void append(E logEvent) {
            // LoggingEventArangoDb loggingEventArangoDb = new LoggingEventArangoDb((LoggingEvent) logEvent);
            if (arangodb != null) {
                ILoggingEvent eventObject = (ILoggingEvent) logEvent;
                BaseDocument document = new BaseDocument();
                document.addAttribute("dupa",eventObject);
                arangodb.insertDocument(document);
            } else {
                arangoDBAsync.insertDocument(logEvent);
            }
        }
    }
}
