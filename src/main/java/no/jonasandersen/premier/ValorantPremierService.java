package no.jonasandersen.premier;

import java.time.LocalDate;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ValorantPremierService implements ApplicationListener<ApplicationReadyEvent> {

  private final Logger logger = LoggerFactory.getLogger(ValorantPremierService.class);
  private final PremierProperties properties;
  private final ValorantPremierEntryRepository repository;
  private final RestTemplate restTemplate = new RestTemplate();

  public ValorantPremierService(PremierProperties properties, ValorantPremierEntryRepository repository) {
    this.properties = properties;
    this.repository = repository;
  }

  @Override
  public void onApplicationEvent(ApplicationReadyEvent event) {
    logger.info("Application ready");

    if (properties.checkOnStartup()) {
      logger.info("Check on startup is enabled. Checking playtime now.");
      checkPlayTime();
      return;
    }
    logger.info("Check on startup is disabled.");
  }


  record Content(String content) {

  }


  @Scheduled(cron = "${premier.schedule}")
  public void checkPlayTime() {
    logger.info("Checking playtime for {}", LocalDate.now());
    Optional<ValorantPremierEntry> opt = repository.findByDate();

    if (opt.isPresent()) {
      ValorantPremierEntry entry = opt.get();

      logger.info("Found entry for {}", LocalDate.now());
      logger.info("The first LocalDateTime that matches today's date is {}", entry.date());

      String text = properties.text();

      String formatted = text.formatted(entry.date().toLocalTime(), entry.map());
      sendMessage(formatted);

    } else {
      logger.info("No entry found for {}", LocalDate.now());
    }

  }

  private void sendMessage(String message) {
    logger.info("Sending message to webhook: \n {}", message);
    final Content content = new Content(message);
    restTemplate.postForObject(
        properties.webhook(),
        content, String.class);
  }

}
