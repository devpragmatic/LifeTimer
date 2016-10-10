package pl.devpragmatic.lifetimer.service;

import java.util.List;
import org.springframework.stereotype.Service;
import pl.devpragmatic.lifetimer.domain.Time;

/**
 * @author devpragmatic
 *
 */
@Service
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

    /**
     * Return all times from repository
     * @return list of times
     */
    public List<Time> getAll();

}
