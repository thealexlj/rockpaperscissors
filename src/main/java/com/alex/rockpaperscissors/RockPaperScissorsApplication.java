package com.alex.rockpaperscissors;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "Rock Paper Scissors API",
                version = "1.0",
                description = "API to play Rock, Paper, Scissors"
        )
)
@SpringBootApplication
public class RockPaperScissorsApplication {

  public static void main(String[] args) {
    SpringApplication.run(RockPaperScissorsApplication.class, args);
  }

}
