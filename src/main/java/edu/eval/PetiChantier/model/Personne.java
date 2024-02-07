package edu.eval.PetiChantier.model;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eval.PetiChantier.vue.VueSite;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(VueSite.class)
    protected Integer id;

    @JsonView(VueSite.class)    protected String nom;

    @JsonView(VueSite.class)    protected String prenom;

    protected String email;
    protected String motDePasse;
    protected boolean admin;

    @JsonView(VueSite.class)    @OneToMany(mappedBy = "responsable")
    protected List<Site> listeChantiers = new ArrayList<>();

}
