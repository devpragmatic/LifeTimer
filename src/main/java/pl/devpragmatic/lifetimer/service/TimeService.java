/**
 * 
 */
package pl.devpragmatic.lifetimer.service;

import pl.devpragmatic.lifetimer.domain.Time;

/**
 * @author devpragmatic
 *
 */
public interface TimeService {
    /**
     * Adding time
     * @param time object time
     */
    public void add(Time time);
    /**
     * Deleting time
     * @param time object time
     */
    public void delete(Time time);

}
