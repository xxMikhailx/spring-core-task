package com.epam.spring.core.config;

import com.epam.spring.core.domain.EventType;
import com.epam.spring.core.logger.EventLogger;
import com.epam.spring.core.logger.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.text.DateFormat;
import java.util.*;

@Configuration
public class LoggersConfig {

    @Autowired
    private CacheFileEventLogger cacheFileEventLogger;

    @Autowired
    private CombinedEventLogger combinedEventLogger;

    @Autowired
    private ConsoleEventLogger consoleEventLogger;

    @Autowired
    private DBLogger dbLogger;

    @Autowired
    private FileEventLogger fileEventLogger;

    @Bean
    public Date date(){
        return new Date();
    }

    @Bean
    public DateFormat dateFormat(){
        return DateFormat.getDateTimeInstance();
    }

    @Bean
    public List<EventLogger> eventLoggers(){
        List<EventLogger> loggers = new ArrayList<EventLogger>();
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        loggers.add(dbLogger);
        return loggers;
    }

    @Autowired
    @Bean
    @DependsOn("dataSource")
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        System.out.println("JDBCTemplate created!");
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap(){
        Map<EventType, EventLogger> loggerMap = new EnumMap<EventType, EventLogger>(EventType.class);
        loggerMap.put(EventType.INFO, consoleEventLogger);
        loggerMap.put(EventType.ERROR, combinedEventLogger);
        return loggerMap;
    }

    @Bean
    public EventLogger defaultLogger(){
        return cacheFileEventLogger;
    }
}
