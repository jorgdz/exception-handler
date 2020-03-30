package com.github.com.jorgdz.app.controllers;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.com.jorgdz.app.entity.Movie;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.MovieService;

@Controller
@RequestMapping(value = "/v1")
public class MovieController {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {	
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@Autowired
	private MovieService serviceMovie;
		
	@GetMapping(value = "/movies", produces = "application/json")
	public @ResponseBody Collection<Movie> index ()
	{
		return this.serviceMovie.all();
	}
	
	@GetMapping(value = "/movie/{id}", produces = "application/json")
	public ResponseEntity<?> show (@PathVariable Long id)
	{
		Movie movie = this.serviceMovie.find(id);		
		if(movie == null)
		{
			//return new ResponseEntity<> ("{\"error\":\"Not found\"}", HttpStatus.NOT_FOUND);
			throw new NotFoundException("No se ha encontrado una película con el Id ".concat(id.toString()));
		}
		
		return new ResponseEntity<> (movie, HttpStatus.FOUND);
	}
	
	@GetMapping(value = "/movies/{date}", produces = "application/json")
	public ResponseEntity<?> getAllByDate (@PathVariable(name = "date", required = false) Date date)
	{
		Collection<Movie> movies = this.serviceMovie.findByDate(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());		
		return new ResponseEntity<> (movies, HttpStatus.FOUND);
	}
	
	@PostMapping(value = "/movie", produces = "application/json")
	public ResponseEntity<?> save (@RequestBody Movie movie)
	{
		Movie movieAdd = this.serviceMovie.add(movie);
		return new ResponseEntity<>(movieAdd, HttpStatus.CREATED);
	}
	
}
