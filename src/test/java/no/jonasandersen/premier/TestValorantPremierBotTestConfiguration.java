package no.jonasandersen.premier;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
public class TestValorantPremierBotTestConfiguration {

  @Bean
  @ServiceConnection
  MariaDBContainer<?> mariaDbContainer() {
    return new MariaDBContainer<>(DockerImageName.parse("mariadb:10.11"));
  }

}
