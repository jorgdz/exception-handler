package com.github.com.jorgdz.app.services;

import java.time.LocalDate;
import java.util.Collection;

import com.github.com.jorgdz.app.entity.Movie;

public interface MovieService {
	
	Collection<Movie> all();
	
	Collection<Movie> findByDate (LocalDate date);
	
	Movie find(Long id);
	
	Movie add (Movie movie);
		
}
