package pl.devpragmatic.lifetimer.service;

import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import org.mockito.runners.MockitoJUnitRunner;

import pl.devpragmatic.lifetimer.domain.Time;
import pl.devpragmatic.lifetimer.repository.TimeRepository;

/**
 * @author devpragmatic
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeServiceImplTest {

    @InjectMocks
    private final TimeService timeService = new TimeServiceImpl();

    @Mock
    private TimeRepository timeRepository;

    private Time time;
    private final Time parent = mock(Time.class);

    @Before
    public void setUp() {
        time = new Time();
    }

    @After
    public void tearDown() {
        Mockito.reset(timeRepository);
    }

    @Test
    public void givenSomethingTimeWhenUseAddThenSaveIt() {
        time = Mockito.mock(Time.class);

        timeService.add(time);

        Mockito.verify(timeRepository).save(time);
    }

    @Test
    public void givenSomethingTimeWithParentWhenUseAddThenAddTimeToParent() {
        int days = 1, hours = 2, minutes = 3, seconds = 4;
        time = new Time();
        time.setTime(days, hours, minutes, seconds);
        time.setParent(parent);

        timeService.add(time);

        Mockito.verify(timeRepository).save(time);
        Mockito.verify(parent).addTime(time);
        Mockito.verify(timeRepository).save(parent);
    }

    @Test
    public void givenSomethingTimeWhenUseDeleteThenDeleteIt() {
        time = Mockito.mock(Time.class);

        timeService.delete(time);

        Mockito.verify(timeRepository).delete(time);
    }

    @Test
    public void givenSomethingTimeWithParentWhenUseDeleteThenSubTimeFromParent() {
        int days = 4, hours = 3, minutes = 2, seconds = 1;
        time = new Time();
        time.setTime(days, hours, minutes, seconds);
        time.setParent(parent);

        timeService.delete(time);

        Mockito.verify(timeRepository).delete(time);
        Mockito.verify(parent).subTime(time);
        Mockito.verify(timeRepository).save(parent);
    }

    @Test
    public void whenUseFindAllThenGetAllTimesFromRepository(){
        List<Time> expectedResult = mock(List.class);
        Mockito.when(timeRepository.findAll()).thenReturn(expectedResult);
        List<Time> result = timeService.getAll();
        Assert.assertEquals(expectedResult, result);
    }
}
