package com.app.mamuvi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.model.Movie;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  MovieDao movieDao;

  @PostMapping
  public ResponseEntity<Movie> createMovie(@RequestBody MovieDTO movieDTO) {
    Movie movie = Movie.mapFromMovieDTO(movieDTO);
    Long movieId = movieDao.saveMovie(movie);
    movie.setId(movieId);
    return ResponseEntity.ok(movie);
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<MovieDTO> getMovieById(@PathVariable("id") Long id){
    MovieDTO movieDTO = movieDao.getMovieById(id);
    return ResponseEntity.ok(movieDTO);
  }
  
}
