package com.app.mamuvi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.mamuvi.model.Greeting;

@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping
  public ResponseEntity<Greeting> getGreeting(){
    Greeting gr = new Greeting("xin chao");
    return ResponseEntity.ok(gr);
  }
}
