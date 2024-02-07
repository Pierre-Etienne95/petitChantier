package edu.eval.PetiChantier.controller;

import com.fasterxml.jackson.annotation.JsonView;
import edu.eval.PetiChantier.dao.PersonneDao;
import edu.eval.PetiChantier.model.Personne;
import edu.eval.PetiChantier.security.JwtService;
import edu.eval.PetiChantier.security.UserService;
import edu.eval.PetiChantier.vue.VueSite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PersonneController {

    @Autowired
    protected PersonneDao personneDao;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Autowired
    protected UserService userService;

    @Autowired
    protected AuthenticationProvider authenticationProvider;

    @Autowired
    protected JwtService jwtService;

    @GetMapping("/personne/{id}")
    @JsonView(VueSite.class)
    public ResponseEntity<Personne> get(@PathVariable int id){

        Optional<Personne> optionalPersonne = personneDao.findById(id);

        if (optionalPersonne.isPresent()){
            return new ResponseEntity<>(optionalPersonne.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/personnes")
    @JsonView(VueSite.class)
    public List<Personne> getAll(){


        List <Personne> listePersonne = personneDao.findAll();

        return listePersonne;
    }

    @DeleteMapping("/personne/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id){

        if(personneDao.existsById(id)) {

            personneDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/personne")
    public ResponseEntity<Personne> add (@RequestBody Personne personne){

        //si c'est une creation
        if(personne.getId()==null){

            personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));

            personneDao.save(personne);
            return new ResponseEntity<>(personne, HttpStatus.CREATED);
        }

        //si c'est une modif
        if(personneDao.existsById(personne.getId())){
            personneDao.save(personne);
            return new ResponseEntity<>(personne, HttpStatus.OK);
        }

        //si l'utilisateur tante de modif un enregistrement qui n'existe pas/plus
        return new ResponseEntity<>(personne, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public String login(@RequestBody Personne personne){

        UserDetails userDetails = userService.loadUserByUsername(personne.getEmail());

        try {
                authenticationProvider.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                personne.getEmail(),
                                personne.getMotDePasse()
                        )
                );
            }catch (AuthenticationException e){
            throw e;
        }
        return jwtService.generateJwt(userDetails);
    }
}
