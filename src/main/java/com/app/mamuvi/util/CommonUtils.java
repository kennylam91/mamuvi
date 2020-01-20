package com.app.mamuvi.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
  
  private CommonUtils() {};
  
  static final String VNCHARS = 
  "áàảãạâấầẩẫậăắằẳẵặđéèẻẽẹêếềểễệíìỉĩịóòỏõọôốồổỗộơớờởỡợúùủũụưứừửữựýỳỷỹỵÁÀẢÃẠÂẤẦẨẪẬĂẮẰẲẴẶĐÉÈẺẼẸÊẾỀỂỄỆÍÌỈĨỊÓÒỎÕỌÔỐỒỔỖỘƠỚỜỞỠỢÚÙỦŨỤƯỨỪỬỮỰÝỲỶỸỴ";
  
  static final String ENCHARS =
  "aaaaaaaaaaaaaaaaadeeeeeeeeeeeiiiiiooooooooooooooooouuuuuuuuuuuyyyyyAAAAAAAAAAAAAAAAADEEEEEEEEEEEIIIIIOOOOOOOOOOOOOOOOOUUUUUUUUUUUYYYYY";

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
  
  public static String convertVnToEn(String vnStr) {
    return Arrays.stream(vnStr.split(""))
    .map(s -> {
      int idx = StringUtils.indexOf(VNCHARS,s);
      return (idx > -1)?ENCHARS.substring(idx, idx+1):s;
    })
    .collect(Collectors.joining());
  }
  
  
}
