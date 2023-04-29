package mk.ukim.finki.emt.lab_bookshop.model.exeptions;

public class CountryNotFoundException extends RuntimeException{
    public CountryNotFoundException(Long id) {
        super(String.format("Country with id: %d is not found", id));
    }
}