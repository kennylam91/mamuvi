package com.app.mamuvi.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dao.impl.AppParamDaoImpl;
import com.app.mamuvi.dto.AppParamDTO;

@RestController
@RequestMapping("/app-params")
public class AppParamController {
  
  @Autowired
  AppParamDaoImpl appParamDao;

  @PostMapping
  public ResponseEntity<List<AppParamDTO>> findAppParamsByIds(@RequestBody Long[] appParamIds){
    List<AppParamDTO> namelist = appParamDao.findAppParamsByIds(Arrays.asList(appParamIds));
    return new ResponseEntity<>(namelist, HttpStatus.OK);
  }
}
