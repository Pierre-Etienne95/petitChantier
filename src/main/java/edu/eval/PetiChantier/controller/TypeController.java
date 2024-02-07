package edu.eval.PetiChantier.controller;

import edu.eval.PetiChantier.dao.Type;
;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class TypeController {

    @Autowired
    protected Type typeDao;

    @GetMapping("/type/{id}")
    public ResponseEntity<edu.eval.PetiChantier.model.Type> get(@PathVariable int id){

        Optional<edu.eval.PetiChantier.model.Type> optionalType = typeDao.findById(id);

        if (optionalType.isPresent()){
            return new ResponseEntity<>(optionalType.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/types")
    public List<edu.eval.PetiChantier.model.Type> getAll(){


        List <edu.eval.PetiChantier.model.Type> listeType = typeDao.findAll();

        return listeType;
    }

    @DeleteMapping("/type/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id){

        if(typeDao.existsById(id)) {

            typeDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/type")
    public ResponseEntity<edu.eval.PetiChantier.model.Type> add (@RequestBody edu.eval.PetiChantier.model.Type type){

        //si c'est une creation
        if(type.getId()==null){
            typeDao.save(type);
            return new ResponseEntity<>(type, HttpStatus.CREATED);
        }

        //si c'est une modif
        if(typeDao.existsById(type.getId())){
            typeDao.save(type);
            return new ResponseEntity<>(type, HttpStatus.OK);
        }

        //si l'utilisateur tante de modif un enregistrement qui n'existe pas/plus
        return new ResponseEntity<>(type, HttpStatus.BAD_REQUEST);
    }

}
