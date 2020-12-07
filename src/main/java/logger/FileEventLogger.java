package logger;

import events.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/*
    Класс для записи событий в файл.
    Получает путь к файлу, проверяет существует ли файл и можно
    ли сделать в него запись. Если да, то добавляет событие.
 */

public class FileEventLogger implements EventLogger {
    private File file;
    private String fileName;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void init() {
        this.file = new File(fileName);
        if(file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("Can't write to file " + fileName);
        }else if(!file.exists()){
            try {
                file.createNewFile();
            }catch (Exception e){
                throw new IllegalArgumentException("Can't create file ", e);
            }

        }
    }

    @Override
    public void logEvent(Event event) {
        try{
            FileUtils.writeStringToFile(file, event.toString(), true);
        }catch (IOException ioe){
            System.err.printf("The mistake in %s has been occurred.%n%s", FileEventLogger.class.getSimpleName(), ioe.getMessage());
        }
    }
}
