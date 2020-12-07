package events;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/*
    Класс передаваемого события
 */

public class Event{
    private int id = new Random().nextInt(100);;
    private String message;
    private Date date;
    private DateFormat dateFormat;

    public Event(Date date, DateFormat dateFormat) {
        this.date = date;
        this.dateFormat = dateFormat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", date=" + dateFormat.format(date) +
                '}' + "\n";
    }
}
