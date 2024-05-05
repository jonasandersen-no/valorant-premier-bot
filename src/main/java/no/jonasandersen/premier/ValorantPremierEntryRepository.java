package no.jonasandersen.premier;

import java.util.Optional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValorantPremierEntryRepository extends CrudRepository<ValorantPremierEntry, Long> {

  @Query("select * from valorant_premier_entry p where date(p.date) = curdate()")
  Optional<ValorantPremierEntry> findByDate();
}
