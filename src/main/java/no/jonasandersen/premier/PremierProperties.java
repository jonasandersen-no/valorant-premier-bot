package no.jonasandersen.premier;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

@ConfigurationProperties(prefix = "premier")
public record PremierProperties(String webhook, String schedule, String text, @DefaultValue("false") Boolean checkOnStartup) {

}
