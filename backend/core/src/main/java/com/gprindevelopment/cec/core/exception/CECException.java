package com.gprindevelopment.cec.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CECException extends RuntimeException {

    public CECException() {
    }

    public CECException(String message) {
        super(message);
    }

    public CECException(String message, Throwable cause) {
        super(message, cause);
    }

    public CECException(Throwable cause) {
        super(cause);
    }

    public CECException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
