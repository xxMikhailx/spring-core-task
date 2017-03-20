package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private Integer cachedSize;
    private List<Event> cache;

    public CacheFileEventLogger(String filename, Integer cachedSize) {
        super(filename);
        this.cachedSize = cachedSize;
        cache = new ArrayList<Event>();
    }

    public void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
        }
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() == cachedSize){
            writeEventsFromCache();
            cache.clear();
        }
    }

    private void writeEventsFromCache(){
        for (Event event : cache) {
            super.logEvent(event);
        }
    }
}
