package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final SeanceService seanceService;

    @Autowired
    public MovieService(MovieRepository movieRepository, SeanceService seanceService) {
        this.movieRepository = movieRepository;
        this.seanceService = seanceService;
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public Boolean ifMovieHasActiveSeance(Movie movie) {
        Movie oldMovie = movieRepository.findById(movie.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        long count = seanceService.countByMovieId(movie.getId());
        if (count > 0) {
            return movie.getLength() <= oldMovie.getLength();
        }
        return true;
    }


    public List<Movie> findAllOrderByNewest() {
        return movieRepository.findAllOrderCreatedOn();
    }

    public Optional<Movie> getById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Boolean deleteById(Long id) {
        if (seanceService.countByMovieId(id) == 0) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
