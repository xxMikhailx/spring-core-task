package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;
import org.springframework.jdbc.core.JdbcTemplate;

public class DBLogger implements EventLogger {

    private JdbcTemplate jdbcTemplate;

    public DBLogger(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void logEvent(Event event) {
        jdbcTemplate.update("INSERT INTO event (id,msg) VALUES (?,?)",
                event.getId(),
                event.toString());
    }
}
