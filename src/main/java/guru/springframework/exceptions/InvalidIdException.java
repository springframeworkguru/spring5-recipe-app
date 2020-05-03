package guru.springframework.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidIdException extends RuntimeException{
    public InvalidIdException(){

    }

    public InvalidIdException(String message){
        super(message);
    }

    public InvalidIdException(String message, Throwable cause){
        super(message, cause);
    }

}

