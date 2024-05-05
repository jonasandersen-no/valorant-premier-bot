package no.jonasandersen.premier;

import java.time.LocalDate;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class ValorantPremierService {

  private final Logger logger = LoggerFactory.getLogger(ValorantPremierService.class);
  private final PremierProperties properties;
  private final ValorantPremierEntryRepository repository;
  private final RestTemplate restTemplate = new RestTemplate();

  public ValorantPremierService(PremierProperties properties, ValorantPremierEntryRepository repository) {
    this.properties = properties;
    this.repository = repository;
  }


  record Content(String content) {

  }


  @Scheduled(cron = "${premier.schedule}")
  @Transactional(readOnly = true)
  public void checkPlayTime() {
    logger.info("Checking playtime for {}", LocalDate.now());
    Optional<ValorantPremierEntry> opt = repository.findByDate();

    if (opt.isPresent()) {
      ValorantPremierEntry entry = opt.get();

      logger.info("Found entry for {}", LocalDate.now());
      logger.info("The first LocalDateTime that matches today's date is {}", entry.date());

      String text =
          "Ny Valorant Premier kamp i kveld klokka " + entry.date().toLocalTime() + ".\n**Map: "
              + entry.map() + "**" + "\nMøt opp minst 10 minutter før kampstart.\n\n";

      sendMessage(text);

    } else {
      logger.info("No entry found for {}", LocalDate.now());
    }

  }

  private void sendMessage(String message) {
    logger.info("Sending message to webhook: {}", message);
    final Content content = new Content(message);
    restTemplate.postForObject(
        properties.webhook(),
        content, String.class);
  }

}
