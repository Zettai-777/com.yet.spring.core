package apps;

/*
    Основной класс приложения.
 */

import beans.Client;
import events.Event;
import events.EventType;
import logger.EventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Map<EventType, EventLogger> loggers;
    private Client client;
    private EventLogger defaultLogger;

    public App(Client client, EventLogger eventLogger,
               Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public App() {
    }

    private void logEvent(EventType type, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMessage(message);

        EventLogger logger = loggers.get(type);
        if (logger == null)
            logger = defaultLogger;

        logger.logEvent(event);
    }

    public void logEvents(ApplicationContext context){
        Event event = context.getBean(Event.class);
        logEvent(EventType.INFO, event, "Some event for user 1");

        event = context.getBean(Event.class);
        logEvent(EventType.INFO, event, "One more event for user 1");

        event = context.getBean(Event.class);
        logEvent(EventType.ERROR, event, "Some event for user 2");

        event = context.getBean(Event.class);
        logEvent(null, event, "Some event for user 3");
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) context.getBean("app");

        Client client = context.getBean("client", Client.class);
        System.out.println("Client says:\n" + client.getGreeting());

        app.logEvents(context);

        context.close();
    }
}
