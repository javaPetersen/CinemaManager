package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.repository.MovieRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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
