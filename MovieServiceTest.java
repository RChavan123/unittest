package com.ravin.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ravin.movieservice.MovieService;
import com.ravin.repository.MovieRepository;
import com.ravin.test.model.Movie;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

	@InjectMocks
	private MovieService mService;
	@Mock
	private MovieRepository mRepo;
	
//	private Movie avtarMovie;
//	private Movie titanic;
//
//	@BeforeEach
//	void init() {
//		avtarMovie = new Movie();
//		avtarMovie.setId(1l);
//		avtarMovie.setName("Avtar");
//		avtarMovie.setGenera("Action");
//		avtarMovie.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 25));
//
//		titanic = new Movie();
//		titanic.setId(2l);
//		titanic.setName("titinic");
//		titanic.setGenera("romantic");
//		titanic.setReleaseDate(LocalDate.of(2021, Month.FEBRUARY, 20));
//
//	}

	@Test
	void save() {
		Movie avtar = new Movie();
		avtar.setId(1l);
		avtar.setName("avtar");
		avtar.setGenera("Action");
		avtar.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 20));

		when(mRepo.save(any(Movie.class))).thenReturn(avtar);
		Movie newMovie = mService.save(avtar);
		assertNotNull(newMovie);
		assertThat(newMovie.getName()).isEqualTo("avtar");
	}

	@Test
	@DisplayName("number of movies")
	void getMovies() {
		Movie avtar = new Movie();
		avtar.setId(1l);
		avtar.setName("avtar");
		avtar.setGenera("Action");
		avtar.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 20));

		Movie titanic = new Movie();
		titanic.setId(2l);
		titanic.setName("avtar");
		titanic.setGenera("Action");
		titanic.setReleaseDate(LocalDate.of(2021, Month.FEBRUARY, 23));

		List<Movie> movies = new ArrayList<>();
		movies.add(titanic);
		movies.add(avtar);

		when(mRepo.findAll()).thenReturn(movies);

		List<Movie> list = mService.getAllMovies();
		assertEquals(2, list.size());
		assertNotNull(list);

	}

	@Test
	@DisplayName("object return by id")
	void getMovieById() {
		Movie avtar = new Movie();
		avtar.setId(1l);
		avtar.setName("avtar");
		avtar.setGenera("Action");
		avtar.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 20));
		when(mRepo.findById(anyLong())).thenReturn(Optional.of(avtar));
		Movie newMovie = mService.getMovieById(1l);
		assertNotNull(newMovie);
		assertThat(newMovie.getId()).isEqualTo(1l);

	}

	@Test
	@DisplayName("exception purpose")
	void getMovieIdException() {
		Movie avtar = new Movie();
		avtar.setId(1l);
		avtar.setName("avtar");
		avtar.setGenera("Action");
		avtar.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 20));
		when(mRepo.findById(1l)).thenReturn(Optional.of(avtar));
		assertThrows(RuntimeException.class, () -> {
			mService.getMovieById(2l);
		});

	}

	@Test
	@DisplayName("update movie test")
	void updateMovie() {
		Movie avtar = new Movie();
		avtar.setId(1l);
		avtar.setName("avtar");
		avtar.setGenera("Action");
		avtar.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 20));

		when(mRepo.findById(anyLong())).thenReturn(Optional.of(avtar));
		when(mRepo.save(any(Movie.class))).thenReturn(avtar);
		avtar.setGenera("Fantacy");
		Movie uptdateMovie = mService.updateMovie(avtar, 1l);
		assertNotNull(uptdateMovie);
		assertEquals("Fantacy", uptdateMovie.getGenera());

	}
	@Test
	@DisplayName("delete by id")
	void deleteMovie() {
		Movie avtar = new Movie();
		avtar.setId(1l);
		avtar.setName("avtar");
		avtar.setGenera("Action");
		avtar.setReleaseDate(LocalDate.of(2020, Month.FEBRUARY, 20));
when(mRepo.findById(anyLong())).thenReturn(Optional.of(avtar));
doNothing().when(mRepo).delete(any(Movie.class));

mService.deleteMovies(1l);
verify(mRepo,times(1)).delete(avtar);




		
	}

}
