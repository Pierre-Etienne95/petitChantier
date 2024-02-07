package edu.eval.PetiChantier.dao;

import edu.eval.PetiChantier.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteDao extends JpaRepository<Site, Integer> {
}
