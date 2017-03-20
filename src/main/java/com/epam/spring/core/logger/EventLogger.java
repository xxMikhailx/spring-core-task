package com.epam.spring.core.logger;

import com.epam.spring.core.domain.Event;

public interface EventLogger {

    void logEvent(Event event);

}
