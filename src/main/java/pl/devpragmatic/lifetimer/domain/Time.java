package pl.devpragmatic.lifetimer.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author devpragmatic
 */
@Entity
public class Time implements Serializable {
    private static final int HOURS_IN_DAY = 24;
    private static final int MINUTES_IN_HOUR = 60;
    private static final int SECONDS_IN_MINUTE = 60;
    
    @Id 
    @GeneratedValue
    private Long id;
    private int days;
    private int hours;
    private int minutes;
    private int seconds;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Time parent;
    
    public void setTime(int days, int hours, int minutes, int seconds) {
        minutes = seconds/SECONDS_IN_MINUTE + minutes;
        hours = minutes/MINUTES_IN_HOUR + hours;
        days = hours/HOURS_IN_DAY + days;
        this.days = days;
        this.hours = hours%HOURS_IN_DAY;
        this.minutes = minutes%MINUTES_IN_HOUR;
        this.seconds = seconds%SECONDS_IN_MINUTE;
        if(this.seconds < 0){
            this.seconds += SECONDS_IN_MINUTE;
            this.minutes--;
        }
        if(this.minutes < 0){
            this.minutes += MINUTES_IN_HOUR;
            this.hours--;
        }
        if(this.hours < 0){
            this.hours += HOURS_IN_DAY;
            this.days--;
        }
        if(this.days < 0){
            reset();
        }
    }

    public Long getId() {
        return id;
    }

    public int getDays() {
        return days;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
    
    public int getSeconds() {
        return seconds;
    }

    public Time getParent() {
        return parent;
    }
    
    public boolean hasParent() {
        return parent != null;
    }

    public void setParent(Time parent) {
        this.parent = parent;
    }
    /**
     * Add time to object
     * @param time time to add
     */
    public void addTime(Time time) {
        if(time != null){
            this.days += time.days;
            this.hours += time.hours;
            this.minutes += time.minutes;
            this.seconds += time.seconds;
            this.setTime(days, hours, minutes, seconds);
        }
    }
    /**
     * Sub time from object
     * @param time time to sub
     */
    public void subTime(Time time) {
        if(time != null){
            this.days -= time.days;
            this.hours -= time.hours;
            this.minutes -= time.minutes;
            this.seconds -= time.seconds;
            this.setTime(days, hours, minutes, seconds);
        }
    }

    private void reset(){
        this.days = 0;
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    /**
     * Return time as seconds
     * @return number of seconds
     */
    public Long getAsSeconds(){
        return (((new Long(days)*HOURS_IN_DAY)+hours)*MINUTES_IN_HOUR + minutes)*SECONDS_IN_MINUTE + seconds;
    }
    /**
     * Get time fields as string
     * @return string with fields of time
     */
    public String getAsString(){
        return days + " " + hours + ":" + minutes + ":" + seconds;
    }
            
    @Override
    public String toString() {
        return "Time{" + "id=" + id + ", days=" + days + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + '}';
    }
}
