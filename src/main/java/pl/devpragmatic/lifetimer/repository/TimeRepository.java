package pl.devpragmatic.lifetimer.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.devpragmatic.lifetimer.domain.Time;

/**
 * Repository interface for time
 * @author devpragmatic
 *
 */
@Repository
public interface TimeRepository extends CrudRepository<Time, Long> {

    List<Time> findAll();
}
