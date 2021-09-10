package pl.petersen.cinemamanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.petersen.cinemamanager.entity.Movie;
import pl.petersen.cinemamanager.exception.MovieNotFoundException;
import pl.petersen.cinemamanager.service.MovieService;

import javax.validation.Valid;


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
            movie = movieService.getById(movieId).orElseThrow(MovieNotFoundException::new);
        } else {
            movie = new Movie();
        }
        model.addAttribute("movie", movie);
        return "admin/movie/add-movie-form";
    }

    @PostMapping("/add")
    public String processForm(@Valid Movie movie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/movie/add-movie-form";
        }
        movieService.save(movie);
        return "redirect:/admin/movies/all";
    }

    @PostMapping("/delete")
    public String deleteMovie(Long deleteId) {
        movieService.deleteById(deleteId);
        return "redirect:/admin/movies/all";
    }


}
