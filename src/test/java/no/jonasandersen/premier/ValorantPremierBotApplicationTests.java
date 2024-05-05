package no.jonasandersen.premier;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Disabled
@Import(TestValorantPremierBotTestConfiguration.class)
class ValorantPremierBotApplicationTests {

  @Test
  void contextLoads() {
  }

}
