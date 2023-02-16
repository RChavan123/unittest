package com.ravin.movieservice;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravin.repository.MovieRepository;
import com.ravin.test.model.Movie;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovieService {
	@Autowired
	private final MovieRepository mRepo;

	public Movie save(Movie movie) {

		return mRepo.save(movie);

	}

	public List<Movie> getAllMovies() {
		return mRepo.findAll();

	}

	public Movie getMovieById(Long id) {
		return mRepo.findById(id).orElseThrow(() -> new RuntimeException("movies not found by this id"));
	}


public Movie updateMovie(Movie movie, long id) {
	Movie newMovie=mRepo.findById(id).get();
	newMovie.setId(2);
	newMovie.setName("Fantacy");
	newMovie.setGenera("Action");
	newMovie.setReleaseDate(LocalDate.of(2020,Month.MARCH,10));
	
	return newMovie;
	
}
public void deleteMovies(Long id) {
	Movie newMovie=mRepo.findById(id).get();
	mRepo.delete(newMovie);
	
}
}
