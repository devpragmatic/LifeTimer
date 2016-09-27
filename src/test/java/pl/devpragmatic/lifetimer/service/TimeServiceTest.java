/**
 * 
 */
package pl.devpragmatic.lifetimer.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import pl.devpragmatic.lifetimer.domain.Time;
import pl.devpragmatic.lifetimer.repository.TimeRepository;
import pl.devpragmatic.lifetimer.service.TimeService;

/**
 * @author devpragmatic
 *
 */
public class TimeServiceTest {

	@InjectMocks
	private final TimeService timeService = new TimeServiceImpl();

	@Mock
	private TimeRepository timeRepository;
	
	@Before
	public void setUp() {
	}

	@After
	public void tearDown(){
	}

	@Test
	public void givenSomethingTimeWhenUseAddThenSaveIt() {
		Given:
			Time time = Mockito.mock(Time.class);
		When:
			timeService.add(time);
		Then:
			Mockito.verify(timeRepository).save();
	}

}
