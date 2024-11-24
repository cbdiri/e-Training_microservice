package tn.pi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String theme;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_debut")
    private Date date_debut;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_fin")
    private Date date_fin;

    @Column(length = 50, columnDefinition = "VARCHAR(50) DEFAULT 'En attente'")
    private String etat;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    @OneToOne
    @JoinColumn(name = "formateur_id")
    private Formateur formateur;

    @ManyToMany
    @JoinTable(
            name = "candidat_formation",
            joinColumns = @JoinColumn(name = "formation_id"),
            inverseJoinColumns = @JoinColumn(name = "candidat_id")
    )
    private List<Candidat> candidats = new ArrayList<>();

}
