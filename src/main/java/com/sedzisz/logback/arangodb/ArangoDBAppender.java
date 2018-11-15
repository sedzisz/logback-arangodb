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

    private AranogDBConnection aranogDBConnection;

    @Override
    protected void append(E logEvent) {
        aranogDBConnection.append(logEvent);
    }

    @Override
    public void start() {
        super.start();
        initlizeConnection();
    }

    private void initlizeConnection() {
        aranogDBConnection = AranogDBConnection.builder()
                .host(host)
                .port(port)
                .database(database)
                .collection(collection)
                .user(user)
                .password(password)
                .context(this)
                .build();
    }

    @Override
    public void stop() {
        super.stop();
        aranogDBConnection.stop();
    }
}
