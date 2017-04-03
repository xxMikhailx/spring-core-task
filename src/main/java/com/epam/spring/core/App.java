package com.epam.spring.core;

import com.epam.spring.core.config.AppConfig;
import com.epam.spring.core.config.LoggersConfig;
import com.epam.spring.core.domain.Client;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.EventType;
import com.epam.spring.core.logger.EventLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class App {
    @Autowired
    private Client client;
    @Autowired
    @Qualifier("defaultLogger")
    private EventLogger defaultLogger;
    @Autowired
    @Qualifier("loggerMap")
    private Map<EventType,EventLogger> loggers;

    public App() {
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
//        high coupling
//        App app = new App();
//        app.client = new Client("1", "John Smith");
//        app.eventLogger = new ConsoleEventLogger();

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggersConfig.class);
        ctx.scan("com.epam.spring.core");
        ctx.refresh();
        App app = (App) ctx.getBean("app");
        for (int i = 0; i < 3; i++) {
        app.logEvent((Event)ctx.getBean("event"), null);
        }
        for (int i = 0; i < 3; i++) {
            app.logEvent((Event)ctx.getBean("event"), EventType.INFO);
        }
        for (int i = 0; i < 3; i++) {
            app.logEvent((Event)ctx.getBean("event"), EventType.ERROR);
        }
        ctx.close();
    }

    private void logEvent(Event event, EventType eventType){
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        EventLogger logger = loggers.get(eventType);
        if (logger == null){
            logger = defaultLogger;
        }
        logger.logEvent(event);
    }

}
