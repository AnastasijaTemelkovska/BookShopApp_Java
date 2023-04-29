package mk.ukim.finki.emt.lab_bookshop.model.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoAvailableCopiesLeftException extends RuntimeException{
    public NoAvailableCopiesLeftException() {
        super("No more available copies left!");
    }
}
