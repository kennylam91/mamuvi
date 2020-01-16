
package com.app.mamuvi.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieInfoDTO;
import com.app.mamuvi.model.Movie;
import com.app.mamuvi.service.MovieExtractingService;

@Service("movieExtractingService")
public class MovieExtractingServiceImpl implements MovieExtractingService{
  
  @Autowired
  MovieDao movieDao;
  
  @Autowired
  AppParamDao appParamDao;
  
  Logger log = LogManager.getLogger(MovieExtractingServiceImpl.class);
  
  public void updateMovies() {
    List<MovieInfoDTO> list = getWeeklyTopMoviesOfPhimmoi();
    List<MovieDetailDTO> detailList = new ArrayList<>();
    boolean success = false;
    try {
     detailList = getMoviesFromMovieInfos(list); 
     success = true;
    }catch(Exception e){
      e.printStackTrace();
      success = false;
    }
     
    detailList.stream().forEach(movie -> movieDao.saveMovie(movie));
  }
  
  public List<MovieDetailDTO> getMoviesFromMovieInfos(List<MovieInfoDTO> movieInfos){
    List<MovieDetailDTO> movies = movieInfos.stream()
        .map(movieInfo -> {
          MovieDetailDTO movieDetailDTO = new MovieDetailDTO();
          movieDetailDTO.setImgUrl(movieInfo.getImgUrl());
          movieDetailDTO.setMovieUrl(movieInfo.getMovieUrl());
          movieDetailDTO.setTitle(StringUtils.join(movieInfo.getViTitle(),movieInfo.getEnTitle(),"-"));
          
          try {
            Document doc = Jsoup.connect(movieInfo.getMovieUrl()).get();
            String movieDetailHtml = doc.getElementsByClass("movie-meta-info").html();
            String director = getDirector(movieDetailHtml);
            String language = getLanguage(movieDetailHtml);
            String year = getRealeasedYear(movieDetailHtml);
            String length = getLength(movieDetailHtml);
            String resolution = getResolution(movieDetailHtml);
            String countries = String.join(", ", getCountries(movieDetailHtml));
            String types = String.join(", ", getTypes(movieDetailHtml));
            String prodComs = String.join(", ", getProdCompany(movieDetailHtml));
            
            movieDetailDTO.setCountryName(countries);
            movieDetailDTO.setLanguageName(language);
            movieDetailDTO.setLength(Long.parseLong(StringUtils.getDigits(length)));
            movieDetailDTO.setDirectorName(director);
            movieDetailDTO.setReleasedDate(LocalDate.of(Integer.parseInt(year), 01, 01));
            movieDetailDTO.setResolutionName(resolution);
            movieDetailDTO.setCountryName(countries);
            movieDetailDTO.setMovieTypeName(types);
            movieDetailDTO.setProdCompanies(prodComs);
            
            
//            appParamDao.findAppParamByNameAndType(directorName, "director");
          } catch (IOException e) {
            e.printStackTrace();
          }
          
          return movieDetailDTO;
        })
        .collect(Collectors.toList());
    return movies;
    
  }
  
  

  public List<MovieInfoDTO> getWeeklyTopMoviesOfPhimmoi() {
    String url = "http://phimmoi.com";
    try {
      Document doc = Jsoup.connect(url).get();
      String topweeklymovielistHtml = doc.getElementById("list-top-film-week").html();
      if(topweeklymovielistHtml != null) {
        String[] movieHtmlArr = topweeklymovielistHtml.split("</a></li>");
        return Arrays.stream(movieHtmlArr)
          .map(html -> {
            MovieInfoDTO movieInfo = new MovieInfoDTO();
            String[] movieTitles = StringUtils.split(getMovieTitle(html),"-");
            if(movieTitles.length ==2) {
              movieInfo.setViTitle(StringUtils.trim(movieTitles[0]));
              movieInfo.setEnTitle(StringUtils.trim(movieTitles[1]));
            }
            String imgUrl = getMovieBgImg(html);
            if(!StringUtils.isAllEmpty(imgUrl)) {
              movieInfo.setImgUrl(imgUrl);
            }
            String movieUrl = getMovieUrl(html);
            if(!StringUtils.isAllEmpty(movieUrl)) {
              movieInfo.setMovieUrl(movieUrl);
            }
            return movieInfo;
          })
          .collect(Collectors.toList());
      }
      return Collections.emptyList();
      
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  
  public String getMovieTitle(String movieHtml) {
    String titleRegex = "title=\"([^\"]*)\"";
    Matcher matcher = Pattern.compile(titleRegex)
                              .matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group(1);
    }
    return "";
  }

  public String getDirector(String movieDetailHtml) {
    String directorUrl = Jsoup.parse(movieDetailHtml).getElementsByClass("dd-director").html();
    return Jsoup.parse(directorUrl).getElementsByTag("a").text();
  }
  
  public String getMovieBgImg(String movieHtml) {
    String bgImgRegex = "http.*.jpg";
    Matcher matcher = Pattern.compile(bgImgRegex).matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group();
    }
    return "";
  }
  
  public String getMovieUrl(String movieHtml) {
    String movieUrlRegex = "href=\"(.*)/\">";
    Matcher matcher = Pattern.compile(movieUrlRegex).matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group(1);
    }
    return "";
  }
  
  public List<String> getCountries(String movieHtml) {
    String directorUrl = Jsoup.parse(movieHtml).getElementsByClass("dd-country").html();
    return  Jsoup.parse(directorUrl).getElementsByTag("a").stream()
        .map(Element::text)
        .collect(Collectors.toList());
  }
  
  public List<String> getTypes(String movieHtml) {
    String typesUrl = Jsoup.parse(movieHtml).getElementsByClass("dd-cat").html();
    return  Jsoup.parse(typesUrl).getElementsByTag("a").stream()
        .map(Element::text)
        .collect(Collectors.toList());
  }
  
  public String getResolution(String movieHtml){
    String value = Jsoup.parse(movieHtml).getElementsContainingOwnText("HD").html();
    return value;
    
  }
  
  public List<String> getProdCompany(String movieHtml){
    String regex = "/nha-san-xuat/([^/]*)/";
    Matcher matcher = Pattern.compile(regex).matcher(movieHtml);
    List<String> prodCompanies = new ArrayList<>();
    while(matcher.find()) {
      prodCompanies.add(StringUtils.capitalize(matcher.group(1).replace('-', ' ')));
    }
    return prodCompanies;
  }
  
  public String getLanguage(String movieHtml){
    String value = Jsoup.parse(movieHtml).getElementsContainingOwnText("Phụ đề").html();
    return value;
    
  }
  
  public String getRealeasedYear(String movieHtml) {
    String yearRegex = "(20\\d{2})|(19\\d{2})";
    Matcher matcher = Pattern.compile(yearRegex).matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group();
    }
    return StringUtils.EMPTY;
  }
  
  public String getLength(String movieHtml){
    String value = Jsoup.parse(movieHtml).getElementsContainingOwnText("phút").html();
    return value;
    
  }
  
}
