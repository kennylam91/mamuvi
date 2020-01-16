package com.app.mamuvi.service.impl;


import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.MovieDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.service.MovieService;
import com.app.mamuvi.util.CommonUtils;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

  @Autowired
  MovieDao movieDao;
  
  @Autowired
  AppParamDao appParamDao;
  
  @Override
  public MovieDetailDTO getMovieDetailById(Long id) {
    MovieDetailDTO movieDetailDTO = movieDao.getMovieById(id);
    String countryName = appParamDao.findAppParamsByIds(CommonUtils.splitStringToLongListByComma(movieDetailDTO.getCountry()))
      .stream().map(AppParamDTO::getName)
      .collect(Collectors.joining(", "));
    String movieTypeName = appParamDao.findAppParamsByIds(CommonUtils.splitStringToLongListByComma(movieDetailDTO.getType()))
        .stream().map(AppParamDTO::getName)
        .collect(Collectors.joining(", "));
//    String prodCompanyName = appParamDao.findAppParamsByIds(CommonUtils.splitStringToLongListByComma(movieDetailDTO.getProdCompanies()))
//        .stream().map(AppParamDTO::getName)
//        .collect(Collectors.joining(", "));
    movieDetailDTO.setCountryName(countryName);
    movieDetailDTO.setMovieTypeName(movieTypeName);
    return movieDetailDTO;
  }

  @Override
  public Long saveMovie(MovieDTO movieDTO) {
    return movieDao.saveMovie(movieDTO);
  }

  
}
