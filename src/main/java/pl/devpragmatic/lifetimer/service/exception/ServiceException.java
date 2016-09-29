package pl.devpragmatic.lifetimer.service.exception;

/**
 *
 * @author devpragmatic
 */
public class ServiceException extends RuntimeException{
    public ServiceException(){
        super();
    }
    
    public ServiceException(String message){
        super(message);
    }
}
