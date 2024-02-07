package edu.eval.PetiChantier.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
public class Site {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nom;

    protected int coordgeo;

    protected String adresse;

    @ManyToOne(optional = false)
    protected Personne responsable;


}
