# logback-arangodb  [![Travis CI Build Status](https://api.travis-ci.org/sedzisz/logback-arangodb.svg?branch=master)]

### Using with logback.xml

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration debug="false">

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%date [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="arangodb" class="com.sedzisz.logback.arangodb.ArangoDBAppender">
        <host>localhost</host>
        <port>8529</port>
        <user>logger</user>
        <password>logger</password>
        <database>logback_db-test</database>
        <collection>logs</collection>
    </appender>

    <logger name="com.arangodb" level="INFO">
        <appender-ref ref="STDOUT"/>
    </logger>

    <root>
        <level value="INFO"/>
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="arangodb"/>
    </root>
</configuration>
```
