package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import com.epam.spring.core.logger.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    public void logEvent(Event event){
        System.out.println(event);
    }

}
