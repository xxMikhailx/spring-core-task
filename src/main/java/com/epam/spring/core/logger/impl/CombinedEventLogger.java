package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "eventLoggers")
    private List<EventLogger> loggers;

    public CombinedEventLogger() {
    }

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @PostConstruct
    public void init(){
        loggers = new ArrayList<EventLogger>();
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            logger.logEvent(event);
        }
    }
}
