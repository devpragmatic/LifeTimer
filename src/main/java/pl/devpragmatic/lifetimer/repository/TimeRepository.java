package pl.devpragmatic.lifetimer.repository;

import pl.devpragmatic.lifetimer.domain.Time;

/**
 * Repository interface for time
 * @author devpragmatic
 *
 */
public interface TimeRepository {

    public void save(Time time);
    
    public Time findOneById(String id);
   
    public void delete(Time time);

}
