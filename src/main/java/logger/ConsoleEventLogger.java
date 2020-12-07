package logger;

/*
    Логгер.
    Выводит на консоль переданное ему сообщение.
 */

import events.Event;

public class ConsoleEventLogger implements EventLogger{

    public void logEvent(Event event){
        System.out.println(event);
    }
}
