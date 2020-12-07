package logger;

import events.Event;

import java.util.ArrayList;
import java.util.List;

/*
   Логгер для записи событий в файл из кэша.
   Записывает приходящие события в кэш, при достижение предельного значения размера кэша
   пишет события из него в файл.
 */

public class CacheFileEventLogger extends FileEventLogger {
    private int cacheSize;
    private List<Event> cache ;

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
        this.cache = new ArrayList<Event>(cacheSize);
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheSize){
            writeEventFromCache();
            cache.clear();
        }

    }

    private void writeEventFromCache(){
        cache.stream().forEach(super::logEvent);
    }

    public void destroy(){
        if(!cache.isEmpty())
            writeEventFromCache();
    }
}
