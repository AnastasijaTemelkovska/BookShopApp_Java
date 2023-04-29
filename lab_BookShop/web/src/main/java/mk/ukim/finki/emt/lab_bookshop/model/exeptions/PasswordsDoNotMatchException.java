package mk.ukim.finki.emt.lab_bookshop.model.exeptions;

public class PasswordsDoNotMatchException extends RuntimeException{
    public PasswordsDoNotMatchException() {
        super("Passwords do not match exception.");
    }
}
