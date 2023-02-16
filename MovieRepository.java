package com.ravin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravin.test.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{
	List<Movie> findByGenera(String genrera);
	

}
