package project.Exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(generalErrorMessageException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CustomErrorMessage handleSeatOutOfBoundException(RuntimeException e){
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());
        return body;
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public CustomErrorMessage handleUnauthorizedException(RuntimeException e){
        CustomErrorMessage body = new CustomErrorMessage(e.getMessage());
        return body;
    }
}
