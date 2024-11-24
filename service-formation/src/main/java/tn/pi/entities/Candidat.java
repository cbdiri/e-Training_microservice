package tn.pi.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private int cin;
    private String email;
    private String number;
    @ManyToMany(mappedBy = "candidats")
    private List<Formation> formations = new ArrayList<>();
}
