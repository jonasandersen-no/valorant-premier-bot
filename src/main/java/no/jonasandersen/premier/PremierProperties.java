package no.jonasandersen.premier;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "premier")
public record PremierProperties(String webhook, String schedule) {

}
