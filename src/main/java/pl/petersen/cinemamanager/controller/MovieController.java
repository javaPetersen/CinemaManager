package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.service.MovieService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
@RequestMapping("/admin/movies")
public class MovieController {


    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping("/all")
    public String allMovies(Model model) {
        model.addAttribute("movies", movieService.findAllOrderByNewest());
        return "admin/movie/all-movies";
    }

    @GetMapping("/add")
    public String addMovieForm(@RequestParam(required = false) Long movieId, Model model) {
        Movie movie;
        if (movieId != null) {
            movie = movieService.getById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            movie = new Movie();
        }
        model.addAttribute("movie", movie);
        return "admin/movie/add-movie-form";
    }

    @PostMapping("/add")
    public String processForm(@Valid Movie movie, @RequestParam(required = false) MultipartFile file,
                              BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return "admin/movie/add-movie-form";
        }
        movieService.save(movie, file, request);
        return "redirect:/admin/movies/all";
    }


    @PostMapping("/delete")
    public String deleteMovie(Long deleteId) {
        movieService.deleteById(deleteId);
        return "redirect:/admin/movies/all";
    }


}
