package edu.eval.PetiChantier.dao;

import edu.eval.PetiChantier.model.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneDao extends JpaRepository<Personne, Integer> {

    Optional<Personne>findByEmail(String email);

}
