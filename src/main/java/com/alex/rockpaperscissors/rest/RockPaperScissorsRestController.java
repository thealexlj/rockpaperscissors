package com.alex.rockpaperscissors.rest;

import com.alex.rockpaperscissors.controller.RockPaperScissorsController;
import com.alex.rockpaperscissors.model.Game;
import com.alex.rockpaperscissors.model.Player;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("rockpaperscissors")
public class RockPaperScissorsRestController {

  @Autowired
  private RockPaperScissorsController controller;

  @GetMapping("play")
  public String getPlay(){
    return "1";
  }

  @PostMapping("sendPlay")
  public void sendPlay(@RequestParam String play){

  }

  @GetMapping("scores")
  public List<Game> getScores(@RequestParam Player player) {
    return controller.getScores(player);
  }

  @GetMapping("allScores")
  public List<Game> getAllScores() {
    return controller.getScores();
  }
}
