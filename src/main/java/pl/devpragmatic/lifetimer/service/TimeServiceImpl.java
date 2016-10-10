package pl.devpragmatic.lifetimer.service;

import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import pl.devpragmatic.lifetimer.domain.Time;
import pl.devpragmatic.lifetimer.repository.TimeRepository;
import pl.devpragmatic.lifetimer.service.exception.ServiceException;

/**
 * @author devpragmatic
 *
 */
@Service
public class TimeServiceImpl implements TimeService {

    private final String PARENT_EXCEPTION_CODE = "bad.parent.time";
    @Inject
    private TimeRepository timeRepository;

    /* (non-Javadoc)
         *  @see pl.devpragmatic.lifetimer.service.TimeService.add(pl.devpragmatic.lifetimer.domain.Time)
     */
    public void add(Time time) {
        if (time != null) {
            timeRepository.save(time);
            if (time.hasParent()) {
                addTimeToParent(time);
            }
        }
    }

    private void addTimeToParent(Time time) {
        Time parent = getParent(time);
        parent.addTime(time);
        timeRepository.save(parent);
    }

    /* (non-Javadoc)
         *  @see pl.devpragmatic.lifetimer.service.TimeService.delete(pl.devpragmatic.lifetimer.domain.Time)
     */
    public void delete(Time time) {
        if (time.hasParent()) {
            subTimeFromParent(time);
        }
        timeRepository.delete(time);
    }

    private void subTimeFromParent(Time time) {
        Time parent = getParent(time);
        parent.subTime(time);
        timeRepository.save(parent);
    }

    private Time getParent(Time time) {
        Time parent = timeRepository.findOne(time.getParentId());
        if (parent == null) {
            throw new ServiceException(PARENT_EXCEPTION_CODE);
        }
        return parent;
    }
    /* (non-Javadoc)
         *  @see pl.devpragmatic.lifetimer.service.TimeService.getAll()
     */
    public List<Time> getAll() {
        return timeRepository.findAll();
    }

}
