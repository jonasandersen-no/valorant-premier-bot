package no.jonasandersen.premier;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestValorantPremierBotTestConfiguration.class)
class ValorantPremierBotApplicationTests {

  @Test
  void contextLoads() {
  }

}
