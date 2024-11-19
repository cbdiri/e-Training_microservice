package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Candidat;

public interface CandidatRepository extends JpaRepository<Candidat,Long> {
}
