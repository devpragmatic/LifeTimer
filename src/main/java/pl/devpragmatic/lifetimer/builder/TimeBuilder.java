package pl.devpragmatic.lifetimer.builder;

import pl.devpragmatic.lifetimer.domain.Time;

/**
 * Builder for time
 * @author devpragmatic
 */
public class TimeBuilder {
    private int days = 0;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    
    public TimeBuilder appendDays(int days){
        this.days += days;
        return this;
    }
    
    public TimeBuilder appendHours(int hours){
        this.hours += hours;
        return this;
    }
        
    public TimeBuilder appendSeconds(int seconds){
        this.seconds += seconds;
        return this;
    }
            
    public TimeBuilder appendMinutes(int minutes){
        this.minutes += minutes;
        return this;
    }
    
    public Time toTime(){
        Time time = new Time();
        time.setTime(days, hours, minutes, seconds);
        return time;
    }
    
}
