/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.meteo.redispoc.commons.exception;

/**
 *
 * @author marc
 */

public class HttpStatusMessage {
    
    private String httpStatus;
    private String exceptionType;
    private String message;

    public HttpStatusMessage(String httpStatus, String exceptionType, String message) {
        this.httpStatus = httpStatus;
        this.exceptionType = exceptionType;
        this.message = message;
    }
    
    public String getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(String httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(String exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}