package com.epam.spring.core.config;

import com.epam.spring.core.domain.EventType;
import com.epam.spring.core.logger.EventLogger;
import com.epam.spring.core.logger.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.util.*;

@Configuration
@PropertySource("${DB_PROPS:classpath:db.properties}")
public class LoggersConfig {

    @Autowired
    private Environment environment;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Date date(){
        return new Date();
    }

    @Bean
    public DateFormat dateFormat(){
        return DateFormat.getDateTimeInstance();
    }

    @Resource(name = "cacheFileEventLogger")
    private CacheFileEventLogger cacheFileEventLogger;

    @Resource(name = "combinedEventLogger")
    private CombinedEventLogger combinedEventLogger;

    @Resource(name = "consoleEventLogger")
    private ConsoleEventLogger consoleEventLogger;

    @Resource(name = "dbLogger")
    private DBLogger dbLogger;

    @Resource(name = "fileEventLogger")
    private FileEventLogger fileEventLogger;

    @Bean
    public Collection<EventLogger> eventLoggers(){
        Collection<EventLogger> loggers = new ArrayList<EventLogger>(3);
        loggers.add(consoleEventLogger);
        loggers.add(fileEventLogger);
        loggers.add(dbLogger);
        return loggers;
    }

    @Autowired
    @Bean
    public JdbcTemplate jdbcTemplate(DriverManagerDataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @DependsOn("propertyConfigIn")
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        System.out.println(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
//        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
//        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
//        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_spring");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
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
