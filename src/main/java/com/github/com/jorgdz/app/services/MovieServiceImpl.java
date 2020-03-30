package com.github.com.jorgdz.app.services;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.com.jorgdz.app.entity.Movie;
import com.github.com.jorgdz.app.util.IHelper;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private IHelper help;
	
	private Collection<Movie> movies = new HashSet<Movie>();
	
	@PostConstruct
	public void setMovies ()
	{
		Collection<Movie> addMovies = Arrays.asList(new Movie(1L, "F&F9", this.help.getDate("2020-03-20"), 20.0),
				new Movie(2L, "It2", this.help.getDate("2019-10-14"), 3.5),
				new Movie(3L, "Dr. Dolittle", this.help.getDate("2019-10-14"), 5),
				new Movie(4L, "Avengers End Game", this.help.getDate("2019-04-13"), 4.0));
		
		this.movies.addAll(addMovies);
	}
	
	@Override
	public Collection<Movie> all() {
		return this.movies;
	}

	@Override
	public Collection<Movie> findByDate(LocalDate date) 
	{
		Collection<Movie> moviesByDate = this.movies.stream()
					.filter(m -> m.getDate().equals(date))
					.collect(Collectors.toSet());
		
		return moviesByDate;
	}

	@Override
	public Movie find(Long id) 
	{
		Movie movie = this.movies.stream().filter(m -> m.getId().equals(id)).findAny().orElse(null);
		return movie;
	}

	@Override
	public Movie add(Movie movie) {
		this.movies.add(movie);
		return movie;
	}
	
	@PreDestroy
	public void destroyMovies ()
	{
		this.movies = null;
	}
}
