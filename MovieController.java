package com.ravin.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ravin.movieservice.MovieService;
import com.ravin.test.model.Movie;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieService mService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Movie create(@RequestBody Movie movie) {
		return mService.save(movie);

	}
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public List<Movie> read(){
		return mService.getAllMovies();
	}
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Movie readById(@PathVariable Long id) {
		return mService.getMovieById(id);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMovie(@PathVariable Long id) {
		mService.deleteMovies(id);
	}
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Movie updateMovie(@PathVariable Long id,@RequestBody Movie movie) {
		return mService.updateMovie(movie,id);
		
		
	}

}
