package logger;

import events.Event;

import java.util.List;

public class CombinedEventLogger implements EventLogger{
    private List<EventLogger> loggers;

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for(EventLogger logger : loggers)
            logger.logEvent(event);
    }

    public List<EventLogger> getLoggers() {
        return loggers;
    }
}
