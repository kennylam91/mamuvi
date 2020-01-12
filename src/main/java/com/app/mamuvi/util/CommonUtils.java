package com.app.mamuvi.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
  
  private CommonUtils() {};

  public static List<Long> splitStringToLongListByComma(String str) {
    if (str == null || str.length() == 0) {
      return Collections.emptyList();
    }
    try {
      return Arrays.stream(StringUtils.split(str, ","))
          .map(String::trim)
          .map(Long::parseLong)
          .collect(Collectors.toList());
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }
}
