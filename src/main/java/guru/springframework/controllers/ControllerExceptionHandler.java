package guru.springframework.controllers;

import guru.springframework.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jt on 7/14/17.
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NumberFormatException.class)
    public ModelAndView handleNumberFormat(Exception exception){

        log.error("Handling Number Format Exception");
        log.error(exception.getMessage());

        return genericExceptionMethod(exception, "400 BAD REQUEST");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFound(Exception exception){

        log.error("Handling not found exception");
        log.error(exception.getMessage());

        return genericExceptionMethod(exception, "404 NOT FOUND");
    }

    private ModelAndView genericExceptionMethod(Exception exception, String response) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("response", response);
        mav.addObject("message", exception.getMessage());
        mav.setViewName("error");
        return mav;
    }
}