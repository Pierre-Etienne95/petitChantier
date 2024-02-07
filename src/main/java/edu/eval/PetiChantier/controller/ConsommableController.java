package edu.eval.PetiChantier.controller;

import edu.eval.PetiChantier.dao.ConsommableDao;
import edu.eval.PetiChantier.model.Consommable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ConsommableController {

    @Autowired
    protected ConsommableDao consommableDao;

    @GetMapping("/consommable/{id}")
    public ResponseEntity<Consommable> get(@PathVariable int id){

        Optional<Consommable> optionalConsommable = consommableDao.findById(id);

        if (optionalConsommable.isPresent()){
            return new ResponseEntity<>(optionalConsommable.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/consommables")
    public List<Consommable> getAll(){


        List <Consommable> listeConsommable = consommableDao.findAll();

        return listeConsommable;
    }

    @DeleteMapping("/consommable/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id){

        if(consommableDao.existsById(id)) {

            consommableDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/consommable")
    public ResponseEntity<Consommable> add (@RequestBody Consommable consommable){

        //si c'est une creation
        if(consommable.getId()==null){
            consommableDao.save(consommable);
            return new ResponseEntity<>(consommable, HttpStatus.CREATED);
        }

        //si c'est une modif
        if(consommableDao.existsById(consommable.getId())){
            consommableDao.save(consommable);
            return new ResponseEntity<>(consommable, HttpStatus.OK);
        }

        //si l'utilisateur tante de modif un enregistrement qui n'existe pas/plus
        return new ResponseEntity<>(consommable, HttpStatus.BAD_REQUEST);
    }

}
