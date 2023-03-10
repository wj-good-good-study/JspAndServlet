package myServlet.Gossip;


import java.sql.Time;
import java.util.Date;

public class message{
    String text;
    Date date;
    Time time;
    message(String text,Date date, Time time){
        this.text=text;
        this.date=date;
        this.time=time;
    }

}
