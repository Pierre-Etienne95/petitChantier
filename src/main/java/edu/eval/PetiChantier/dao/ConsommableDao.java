package edu.eval.PetiChantier.dao;

import edu.eval.PetiChantier.model.Consommable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsommableDao extends JpaRepository<Consommable, Integer> {
}
