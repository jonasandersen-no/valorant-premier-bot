package no.jonasandersen.premier;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "valorant_premier_entry", schema = "valorant")
public record ValorantPremierEntry(@Id Long id, String map, LocalDateTime date) {

}
