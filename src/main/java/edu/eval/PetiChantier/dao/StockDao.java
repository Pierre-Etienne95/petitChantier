package edu.eval.PetiChantier.dao;

import edu.eval.PetiChantier.model.Site;
import edu.eval.PetiChantier.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDao extends JpaRepository<Stock, Integer> {
}
