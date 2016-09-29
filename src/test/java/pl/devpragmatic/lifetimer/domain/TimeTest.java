package pl.devpragmatic.lifetimer.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import pl.devpragmatic.lifetimer.builder.TimeBuilder;

/**
 *
 * @author devpragmatic
 */
public class TimeTest {
    private Time time;
    
    @Before
    public void setUp() {
        time = new Time();
    }

    @Test
    public void givenTwentyFiveHoursWhenUseSetTimeThenSetOneDayAndOneHourAndZeroOther() {
        time.setTime(0,25,0,0);
        equalsTime(1, 1, 0, 0, time);
    }
    
    @Test
    public void givenSeventyHoursWhenUseSetTimeThenSetTwoDaysAndTwentyTwoHours() {
        time.setTime(0,70,0,0);
        equalsTime(2, 22, 0, 0, time);
    }
    
    @Test
    public void givenTwoHundredMinutesWhenUseSetTimeThenSetThreeHoursAndTwentyMinutes() {
        time.setTime(0,0,200,0);
        equalsTime(0, 3, 20, 0, time);
    }
    
    @Test
    public void givenThreeThousandMinutesWhenUseSetTimeThenSetTwoDaysAndTwoHours() {
        time.setTime(0,0,3000,0);
        equalsTime(2,2,0,0, time);
    }
    
        
    @Test
    public void givenSixThousandTenSecondsWhenUseSetTimeThenSetOneHoursFourtyMinutesTenSeconds() {
        time.setTime(0,0,0,6010);
        equalsTime(0,1,40,10, time);
    }

    @Test
    public void givenEightySixThousandAndFourHundredSecondsWhenUseSetTimeThenSetOneDay() {
        time.setTime(0,0,0,86400);
        equalsTime(1,0,0,0, time);
    }
    @Test
    public void givenTimeWhenUseAddTimeThenAddThisTime(){
        time.addTime(new TimeBuilder().appendSeconds(86400).toTime());
        equalsTime(1,0,0,0, time);
    }
    
    @Test
    public void givenMinusTimeWhenUseSetTimeThenSetTimeZero(){
        time.setTime(-1, 25, -70, 0);
        equalsTime(0, 0, 0, 0, time);
        time.setTime(-1, 25, -59, -60);
        equalsTime(0, 0, 0, 0, time);
    }
    
    @Test
    public void givenTimeWhenUseSubTimeThenSubThisTime(){
        time = new TimeBuilder().appendDays(2).toTime();
        time.subTime(new TimeBuilder().appendHours(2).appendSeconds(4).toTime());
        equalsTime(1, 21, 59, 56, time);
    }
    
    @Test
    public void givenNullTimeWhenUseSubTimeThenDoNothing(){
        time = new TimeBuilder().appendDays(2).appendMinutes(3).toTime();
        time.subTime(null);
        equalsTime(2, 0, 3, 0, time);
    }
    
        
    @Test
    public void givenNullTimeWhenUseAddTimeThenDoNothing(){
        time = new TimeBuilder().appendDays(4).appendMinutes(5).toTime();
        time.addTime(null);
        equalsTime(4, 0, 5, 0, time);
    }
    
    private void equalsTime(int days, int hours, int minutes, int seconds, Time time) {
        assertEquals(days, time.getDays());
        assertEquals(hours, time.getHours());
        assertEquals(minutes, time.getMinutes());
        assertEquals(seconds, time.getSeconds());
    }
    
}
