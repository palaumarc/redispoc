/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.commons.exception;

import java.io.FileNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author marc
 */
@ControllerAdvice
public class ExceptionController {
    
     private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public HttpStatusMessage illegalArgumentExceptionHandler(Exception e) {
        log.error("Excepció: " + e);
        return new HttpStatusMessage(HttpStatus.BAD_REQUEST.toString(), 
                                     e.getClass().getSimpleName(), 
                                     e.getMessage());
    }
    
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseBody
    public HttpStatusMessage fileNotFoundException(Exception e) {
        log.error("Excepció: " + e);
        return new HttpStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                                     e.getClass().getSimpleName(), 
                                     e.getMessage());
    }
}
