package com.epam.spring.core;

import com.epam.spring.core.domain.Client;
import com.epam.spring.core.domain.Event;
import com.epam.spring.core.domain.EventType;
import com.epam.spring.core.logger.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType,EventLogger> loggers;

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

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

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
