package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.repository.MovieRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Movie> findAllOrderByNewest() {
        return movieRepository.findAllOrderCreatedOn();
    }

    public Optional<Movie> getById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }


}