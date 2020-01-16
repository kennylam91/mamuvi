package com.app.mamuvi.service.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import com.app.mamuvi.dao.AppParamDao;
import com.app.mamuvi.dao.MovieDao;
import com.app.mamuvi.dao.impl.AppParamDaoImpl;
import com.app.mamuvi.dao.impl.MovieDaoImpl;
import com.app.mamuvi.dto.MovieDetailDTO;
import com.app.mamuvi.dto.MovieInfoDTO;
import com.app.mamuvi.service.MovieExtractingService;

public class MovieExtractingServiceImplTest {

  private AppParamDao appParamDao;
  private MovieDao movieDao;
  private MovieExtractingServiceImpl movieExtractingService;
  private String movieDtHtml;
  
  @Before
  public void setup() {
    appParamDao = mock(AppParamDaoImpl.class);
    movieDao = mock(MovieDaoImpl.class);
    movieExtractingService = new MovieExtractingServiceImpl();
    movieDtHtml = "<div class=\"movie-meta-info\" style=\"width: auto; height: 270px; overflow: hidden;\">\r\n" + 
        "                                    <dl class=\"movie-dl\">\r\n" + 
        "                                        <dt class=\"movie-dt\">Trạng thái:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd status\">\r\n" + 
        "                                            Hoàn tất    \r\n" + 
        "                                        </dd><br>                                   \r\n" + 
        "                                        <dt class=\"movie-dt\">Đạo diễn:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd dd-director\"><a href=\"http://www.phimmoi.com/dao-dien/joachim-ronning/\" rel=\"tag\">Joachim Rønning</a></dd><br>\r\n" + 
        "                                        <dt class=\"movie-dt\">Quốc gia:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd dd-country\"><a href=\"http://www.phimmoi.com/quoc-gia/anh/\" rel=\"tag\">Phim Anh</a>, <a href=\"http://www.phimmoi.com/quoc-gia/my/\" rel=\"tag\">Phim Mỹ</a></dd><br>\r\n" + 
        "                                        <dt class=\"movie-dt\">Năm:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\">2019</dd><br>                                      \r\n" + 
        "                                        <dt class=\"movie-dt\">Thời lượng:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\">112 phút</dd><br>\r\n" + 
        "\r\n" + 
        "                                        \r\n" + 
        "                                        <dt class=\"movie-dt\">Chất lượng:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\">\r\n" + 
        "                                            Bản Đẹp \r\n" + 
        "                                        </dd><br>   \r\n" + 
        "                                        <dt class=\"movie-dt\">Độ phân giải:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\">Full HD</dd><br>\r\n" + 
        "\r\n" + 
        "                                        <dt class=\"movie-dt\">Ngôn ngữ:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\">\r\n" + 
        "                                            Phụ Đề Việt \r\n" + 
        "                                        </dd><br>   \r\n" + 
        "\r\n" + 
        "                                        <dt class=\"movie-dt\">Thể loại:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd dd-cat\"><a href=\"http://www.phimmoi.com/phim-vien-tuong/\" rel=\"category tag\">Phim Viễn Tưởng</a>, <a href=\"http://www.phimmoi.com/phim-gia-dinh/\" rel=\"category tag\">Phim Gia Đình</a>, <a href=\"http://www.phimmoi.com/phim-phieu-luu/\" rel=\"category tag\">Phim Phiêu Lưu</a></dd><br>\r\n" + 
        "\r\n" + 
        "                                        <dt class=\"movie-dt\">Nhà sản xuất:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\"><a href=\"http://www.phimmoi.com/nha-san-xuat/roth-films/\" rel=\"tag\">Roth Films</a>, <a href=\"http://www.phimmoi.com/nha-san-xuat/walt-disney-pictures/\" rel=\"tag\">Walt Disney Pictures</a></dd><br>\r\n" + 
        "\r\n" + 
        "                                        <dt class=\"movie-dt\">Lượt xem:</dt>\r\n" + 
        "                                        <dd class=\"movie-dd\">93106</dd><br>\r\n" + 
        "                                        \r\n" + 
        "                                    </dl>\r\n" + 
        "                                    <div class=\"clear\">\r\n" + 
        "                                    </div>\r\n" + 
        "                                </div>";
  }
  
  @Test
  public void testMock() {
    assertNotNull(appParamDao);
    assertNotNull(movieExtractingService);
  }
  
  @Test
  public void testGetDirectorFromMovieDetailHtml() {
    String actual = movieExtractingService.getDirector(movieDtHtml);
    String expected = "Jeff Chan";
    assertEquals(expected,actual );
  }
  
  @Test
  public void testGetCountriesFromMovieDetailHtml() {
    List<String> actual = movieExtractingService.getCountries(movieDtHtml);
    List<String> expected = Arrays.asList("Phim Anh", "Phim Mỹ");
    for(int i = 0; i<actual.size();i++) {
      assertEquals(actual.get(i), expected.get(i)); 
    }
  }
  
  @Test
  public void testGetTypes() {
    List<String> actual = movieExtractingService.getTypes(movieDtHtml);
    List<String> expected = Arrays.asList("Phim Viễn Tưởng", "Phim chính kịch - Drama", "Phim hành động");
    for(int i = 0; i<actual.size();i++) {
      assertEquals(actual.get(i), expected.get(i)); 
    }
  }
  
  @Test
  public void testGetResolution() {
    String actual = movieExtractingService.getResolution(movieDtHtml);
    assertEquals("Full HD", actual);
  }
  
  @Test
  public void testGetProdCompanies() {
    List<String> actual = movieExtractingService.getProdCompany(movieDtHtml);
    List<String> expected = Arrays.asList("Colony pictures");
    for(int i = 0; i<actual.size();i++) {
      assertEquals(actual.get(i), expected.get(i)); 
    }
  }
  
  @Test
  public void testGetLanguage() {
    String actual = movieExtractingService.getLanguage(movieDtHtml);
    assertEquals("Phụ Đề Việt", actual);
  }
  
  @Test
  public void testGetYear() {
    String actual = movieExtractingService.getRealeasedYear(movieDtHtml);
    assertEquals("2019",actual);
  }
  
  @Test
  public void testGetLength() {
    String actual = movieExtractingService.getLength(movieDtHtml);
    assertEquals("94 phút",actual);
  }
  
  @Test
  public void testGetMoviesFromMoviesInfo() {
    MovieInfoDTO movieInfo = new MovieInfoDTO();
    movieInfo.setViTitle("Săn Lùng Dị Nhân");
    movieInfo.setEnTitle("Code 8");
    movieInfo.setMovieUrl("http://www.phimmoi.com/san-lung-di-nhan_48643");
    List<MovieInfoDTO> movieInfoDTOs = new ArrayList<MovieInfoDTO>();
    movieInfoDTOs.add(movieInfo);
    List<MovieDetailDTO> movieDetailDTOs = movieExtractingService.getMoviesFromMovieInfos(movieInfoDTOs);
    assertNotNull(movieDetailDTOs);
  }
}
