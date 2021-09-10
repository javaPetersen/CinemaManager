package pl.petersen.cinemamanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.repository.MovieRepository;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    public void save(Movie movie, MultipartFile poster, HttpServletRequest request) {
        if (poster != null) {
            Path newFile = Paths.get("src/main/webapp/uploads/posters/" +
                    movie.getTitle().replaceAll(" ", "_") + ".jpg");
            try {
                poster.transferTo(newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            movie.setPoster(newFile.toString().substring(15));
        }
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
