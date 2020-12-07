package logger;

/*
    Интерфейс логгирования.
 */

import events.Event;

public interface EventLogger {

    void logEvent(Event event);
}
