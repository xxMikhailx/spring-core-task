package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class CombinedEventLogger implements EventLogger {

    @Autowired
    @Qualifier("eventLoggers")
    private List<EventLogger> loggers;

    public CombinedEventLogger() {
        System.out.println("CombinedEventLogger created!");
    }

    public void logEvent(Event event) {
        for (EventLogger logger : loggers) {
            System.out.println("Using " + logger.getClass().getSimpleName() + "...");
            logger.logEvent(event);
        }
    }
}
