package com.sedzisz.logback.arangodb;

import ch.qos.logback.core.AppenderBase;
import lombok.Setter;

/**
 * @author Zdzisław Sędek <zdzislaw.sedek@gmail.com>
 */
public class ArangoDBAppender<E> extends AppenderBase<E> {

    @Setter
    private String host;
    @Setter
    private int port;
    @Setter
    private String database;
    @Setter
    private String collection;
    @Setter
    private String user;
    @Setter
    private String password;

    private ArangoDBConnection arangoDBConnection;

    @Override
    protected void append(E logEvent) {
        arangoDBConnection.append(logEvent);
    }

    @Override
    public void start() {
        super.start();
        initializeConnection();
    }

    private void initializeConnection() {
        arangoDBConnection = new ArangoDBConnection(this, host, port, user, password, database, collection);
    }

    @Override
    public void stop() {
        super.stop();
        arangoDBConnection.stop();
    }
}
