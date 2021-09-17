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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.petersen.cinemamanager.entity.Movie;
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
            movie = movieService.getById(movieId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        } else {
            movie = new Movie();
        }
        model.addAttribute("movie", movie);
        return "admin/movie/add-movie-form";
    }

    @PostMapping("/add")
    public String processForm(@Valid Movie movie,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (!movieService.ifMovieHasActiveSeance(movie)) {
            redirectAttributes.addFlashAttribute("error",
                    "Nie można zwiększyć czasu trwania filmu, ponieważ ma on aktywny seans");
            return "redirect:/admin/movies/add?movieId=" + movie.getId();
        }

        if (bindingResult.hasErrors()) {
            return "admin/movie/add-movie-form";
        }
        movieService.save(movie);
        return "redirect:/admin/movies/all";
    }


    @PostMapping("/delete")
    public String deleteMovie(Long deleteId, RedirectAttributes redirectAttributes) {
        if (!movieService.deleteById(deleteId)) {
            redirectAttributes.addFlashAttribute("error",
                    "Nie można usunąć filmu, ponieważ jest on aktualnie używany.");
        }
        return "redirect:/admin/movies/all";
    }


}
