package pl.devpragmatic.lifetimer.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private Long parentId;
    
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

    public Long getParentId() {
        return parentId;
    }
    
    public boolean hasParent() {
        return parentId != null;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void addTime(Time time) {
        if(time != null){
            this.days += time.days;
            this.hours += time.hours;
            this.minutes += time.minutes;
            this.seconds += time.seconds;
            this.setTime(days, hours, minutes, seconds);
        }
    }
   
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

    @Override
    public String toString() {
        return "Time{" + "id=" + id + ", days=" + days + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds + ", parentId=" + parentId + '}';
    }
}
