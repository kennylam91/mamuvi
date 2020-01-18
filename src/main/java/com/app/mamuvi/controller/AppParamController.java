package com.app.mamuvi.controller;

import java.awt.PageAttributes.MediaType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.dao.impl.AppParamDaoImpl;
import com.app.mamuvi.dto.AppParamDTO;
import com.app.mamuvi.dto.AppParamSearchDTO;
import com.app.mamuvi.service.AppParamService;

@RestController
@RequestMapping("/app-params")
public class AppParamController {
  
  @Autowired
  AppParamService appParamService;
  
  @PostMapping(produces = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE, 
                consumes = org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
  public ResponseEntity<List<AppParamDTO>> findAppParamsByAPDTO(@RequestBody AppParamSearchDTO apDto){
    List<AppParamDTO> list = appParamService.findAppParamsByAPDTO(apDto);
    return ResponseEntity.ok(list);
  }
}
