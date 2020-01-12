package com.app.mamuvi.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;

public class CommonUtilsTest {

  @Test
  public void testSplitStringToLongListByComma() {
    String input = "1,2,3,4,5";
    List<Long> expected = Arrays.asList(1L,2L,3L,4L,5L);
    assertEquals(expected, CommonUtils.splitStringToLongListByComma(input));
    assertEquals(Collections.emptyList(), CommonUtils.splitStringToLongListByComma(null));
    assertEquals(Arrays.asList(1L), CommonUtils.splitStringToLongListByComma("1"));
    assertEquals(Collections.emptyList(), CommonUtils.splitStringToLongListByComma("1,a"));
  }
}
