package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Formation;

public interface FormationRepository extends JpaRepository<Formation,Long> {
}
