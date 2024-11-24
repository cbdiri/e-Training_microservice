package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Sale;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
