package com.app.mamuvi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieSearchDTO;
import com.app.mamuvi.dto.ResultSetDTO;
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  MovieService movieService;
  

  @PostMapping
  public ResponseEntity<Long> createMovie(@RequestBody MovieDTO movieDTO) {
    Long movieId = movieService.saveMovie(movieDTO);
    if(null != movieId) {
      return new ResponseEntity<>(movieId,HttpStatus.CREATED);
    }
    else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<MovieDetailDTO> getMovieById(@PathVariable("id") Long id){
    MovieDetailDTO movieDetailDTO = movieService.getMovieDetailById(id);
    return (movieDetailDTO != null)?
        ResponseEntity.ok(movieDetailDTO):
        new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
  @PutMapping("/{id}")
  public ResponseEntity<Void> updateMovie(@PathVariable("id") Long id, @RequestBody MovieDTO movieDTO){
    try {
      movieService.updateMovie(movieDTO);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
  
  @GetMapping("/search")
  public ResponseEntity<ResultSetDTO<Movie>> searchMovie(@RequestBody MovieSearchDTO movieSearchDTO){
    ResultSetDTO<Movie> resultSet = movieService.searchMovie(movieSearchDTO);
    if(resultSet != null && !resultSet.getList().isEmpty() && resultSet.getTotalRecords() > 0) {
      return ResponseEntity.ok(resultSet);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
  }
}
