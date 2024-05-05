package no.jonasandersen.premier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PremierProperties.class)
public class ValorantPremierBotApplication {

  public static void main(String[] args) {
    SpringApplication.run(ValorantPremierBotApplication.class, args);
  }

}
