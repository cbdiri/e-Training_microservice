package tn.pi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.pi.entities.Formateur;

public interface FormateurRepository  extends JpaRepository<Formateur,Long> {
}
