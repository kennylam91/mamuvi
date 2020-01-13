
package com.app.mamuvi.bot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MovieExtracting {
  
  public static List<MovieInfo> getWeekTopFilmListOfPhimmoicom() {
    String url = "http://phimmoi.com";
    try {
      Document doc = Jsoup.connect(url).get();
      String topweeklymovielistHtml = doc.getElementById("list-top-film-week").html();
      if(topweeklymovielistHtml != null) {
        String[] movieHtmlArr = topweeklymovielistHtml.split("</a></li>");
        return Arrays.stream(movieHtmlArr)
          .map(html -> {
                MovieInfo movieInfo = new MovieInfo();
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

  public static void main(String[] args) {
    
  }
  
  public static String getMovieTitle(String movieHtml) {
    String titleRegex = "title=\"([^\"]*)\"";
    Matcher matcher = Pattern.compile(titleRegex)
                              .matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group(1);
    }
    return "";
  }
  
  
  public static String getMovieBgImg(String movieHtml) {
    String bgImgRegex = "http.*.jpg";
    Matcher matcher = Pattern.compile(bgImgRegex).matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group();
    }
    return "";
  }
  
  public static String getMovieUrl(String movieHtml) {
    String movieUrlRegex = "href=\"(.*)/\">";
    Matcher matcher = Pattern.compile(movieUrlRegex).matcher(movieHtml);
    if(matcher.find()) {
      return matcher.group(1);
    }
    return "";
  }
}
