package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("dbLogger")
@DependsOn("jdbcTemplate")
public class DBLogger implements EventLogger {

    @Resource(name = "jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    public DBLogger(){
        System.out.println("DBLogger created!");
    }

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void logEvent(Event event) {
        System.out.println("DBLogger works!");
        jdbcTemplate.update("INSERT INTO event (id,msg) VALUES (?,?)",
                event.getId(),
                event.toString());
    }
}
