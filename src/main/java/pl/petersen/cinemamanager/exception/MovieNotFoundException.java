package pl.petersen.cinemamanager.exception;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException() {
        super("Movie not found");
    }
}
