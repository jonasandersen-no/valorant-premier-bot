package no.jonasandersen.premier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties(PremierProperties.class)
@EnableScheduling
public class ValorantPremierBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(ValorantPremierBotApplication.class, args);
  }

}
