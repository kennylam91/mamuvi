package com.app.mamuvi.util;

import static org.junit.Assert.assertNotNull;
import java.util.List;
import org.junit.Test;

public class HtmlUtilsTest {

  @Test
  public void testGetMovieDetail() {
    String html = "<dl class=\"movie-dl\"> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Trạng thái:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd status\">\r\n" + 
        "   Hoàn tất \r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Đạo diễn:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd dd-director\">\r\n" + 
        "  <a href=\"http://www.phimmoi.com/dao-dien/jeff-chan/\" rel=\"tag\">Jeff Chan</a>\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Quốc gia:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd dd-country\">\r\n" + 
        "  <a href=\"http://www.phimmoi.com/quoc-gia/canada/\" rel=\"tag\">Phim Canada</a>\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Năm:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "  2019\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Thời lượng:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "  94 phút\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Chất lượng:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "   Bản Đẹp \r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Độ phân giải:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "  Full HD\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Ngôn ngữ:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "   Phụ Đề Việt \r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Thể loại:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd dd-cat\">\r\n" + 
        "  <a href=\"http://www.phimmoi.com/phim-vien-tuong/\" rel=\"category tag\">Phim Viễn Tưởng</a>, \r\n" + 
        "  <a href=\"http://www.phimmoi.com/phim-chinh-kich-drama/\" rel=\"category tag\">Phim chính kịch - Drama</a>, \r\n" + 
        "  <a href=\"http://www.phimmoi.com/phim-hanh-dong/\" rel=\"category tag\">Phim hành động</a>\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Nhà sản xuất:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "  <a href=\"http://www.phimmoi.com/nha-san-xuat/colony-pictures/\" rel=\"tag\">Colony Pictures</a>\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        " <dt class=\"movie-dt\">\r\n" + 
        "  Lượt xem:\r\n" + 
        " </dt> \r\n" + 
        " <dd class=\"movie-dd\">\r\n" + 
        "  13892\r\n" + 
        " </dd>\r\n" + 
        " <br> \r\n" + 
        "</dl> \r\n" + 
        "<div class=\"clear\"> \r\n" + 
        "</div>";
    List<String> actual = HtmlUtils.getMovieDetail(html);
    assertNotNull(actual);
  }
}
