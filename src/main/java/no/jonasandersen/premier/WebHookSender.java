package no.jonasandersen.premier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WebHookSender implements ApplicationListener<ApplicationReadyEvent> {

  private static final Logger log = LoggerFactory.getLogger(WebHookSender.class);

  private final RestTemplate restTemplate = new RestTemplate();
  private final PremierProperties properties;
  private final ValorantPremierService service;

  public WebHookSender(PremierProperties properties, ValorantPremierService service) {
    this.properties = properties;
    this.service = service;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    onReady();

    service.checkPlayTime();
  }

  record Content(String content) {

  }

  public void onReady() {

    String message = "Ready to send messages to webhook";

    log.info("Sending message to webhook: {}", message);
    final Content content = new Content(message);
    restTemplate.postForObject(
        properties.webhook(),
        content, String.class);


  }
}
