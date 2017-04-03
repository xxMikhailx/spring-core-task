package com.epam.spring.core.logger.impl;

import com.epam.spring.core.domain.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {
    @Value("5")
    private Integer cachedSize;
    private List<Event> cache;

    public CacheFileEventLogger(){
    }

    public CacheFileEventLogger(String filename) {
        super(filename);
    }

    public CacheFileEventLogger(String filename, Integer cachedSize) {
        super(filename);
        this.cachedSize = cachedSize;
        cache = new ArrayList<Event>();
    }

    @PostConstruct
    public void init(){
        this.cache = new ArrayList<Event>(cachedSize);
    }

    @PreDestroy
    public void destroy(){
        if (!cache.isEmpty()){
            writeEventsFromCache();
            cache.clear();
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
