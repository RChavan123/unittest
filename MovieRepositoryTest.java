package com.ravin.test.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ravin.repository.MovieRepository;
import com.ravin.test.model.Movie;

@DataJpaTest
public class MovieRepositoryTest {
	@Autowired
	private MovieRepository mRepo;

	private Movie avtarMovie;
	private Movie titanic;

	@BeforeEach
	void init() {
		avtarMovie = new Movie();
		avtarMovie.setName("Avtar");
		avtarMovie.setGenera("Action");
		avtarMovie.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 25));

		titanic = new Movie();
		titanic.setName("titinic");
		titanic.setGenera("romantic");
		titanic.setReleaseDate(LocalDate.of(2021, Month.FEBRUARY, 20));

	}

	@Test
	@DisplayName("save onemovies")
	void save() {
//		arrange

		Movie newMovie = mRepo.save(avtarMovie);
//		assert
		assertNotNull(newMovie);
		assertThat(newMovie.getId()).isNotEqualTo(null);

	}

	@Test
	@DisplayName("save allmovies")
	void getAllMovies() {

		mRepo.save(avtarMovie);

		titanic.setGenera("romantic");
		titanic.setReleaseDate(LocalDate.of(2021, Month.FEBRUARY, 20));
		mRepo.save(avtarMovie);

		List<Movie> list = mRepo.findAll();
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(2, list.size());

	}

	@Test
	@DisplayName("movie find by id")
	void getMoviesById() {

		mRepo.save(avtarMovie);
		Movie existingMovies = mRepo.findById(avtarMovie.getId()).get();
		assertNotNull(avtarMovie);
		assertEquals("Action", existingMovies.getGenera());
		assertThat(avtarMovie.getReleaseDate()).isBefore(LocalDate.of(2020, Month.FEBRUARY, 25));

	}

	@Test
	@DisplayName("deleting movies by id")
	void deleteMovie() {

		mRepo.save(avtarMovie);
		long id = avtarMovie.getId();

		mRepo.save(titanic);
		mRepo.delete(avtarMovie);
		Optional<Movie> existingMovie = mRepo.findById(id);
		List<Movie> list = mRepo.findAll();

		assertEquals(1, list.size());
		assertThat(existingMovie).isEmpty();

	}

	@Test
	@DisplayName("uptate movie")
	void updateMovie() {

		mRepo.save(avtarMovie);

		Movie existingMovies = mRepo.findById(avtarMovie.getId()).get();
		existingMovies.setGenera("fantacy");

		Movie newMovie = mRepo.save(existingMovies);
		assertEquals("fantacy", newMovie.getGenera());
		assertEquals("avtar", newMovie.getName());

	}

	@Test
	@DisplayName("movies find by genera")
	void getMoviesByGenera() {

		mRepo.save(avtarMovie);
		mRepo.save(titanic);

		List<Movie> list = mRepo.findByGenera("romantic");
		assertNotNull(list);
		assertThat(list.size()).isEqualTo(1);

	}

}
