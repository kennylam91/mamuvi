package com.app.mamuvi.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;

public class HtmlUtils {

  public static List<String> getMovieDetail(String movieDetailHtml) {
    Pattern pattern = Pattern.compile(">([\\w\\s-:]{5,})<");
    Matcher matcher = pattern.matcher(movieDetailHtml);
    List<String> list = new ArrayList<>();
    while(matcher.find()) {
      list.add(matcher.group());
    }
    return list;
  }
}
