package edu.eval.PetiChantier.controller;

import edu.eval.PetiChantier.dao.LocalDao;
import edu.eval.PetiChantier.model.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class LocalController {

    @Autowired
    protected LocalDao localDao;

    @GetMapping("/local/{id}")
    public ResponseEntity<Local> get(@PathVariable int id){

        Optional<Local> optionalLocal = localDao.findById(id);

        if (optionalLocal.isPresent()){
            return new ResponseEntity<>(optionalLocal.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/locals")
    public List<Local> getAll(){


        List <Local> listeLocal = localDao.findAll();

        return listeLocal;
    }

    @DeleteMapping("/local/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id){

        if(localDao.existsById(id)) {

            localDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/local")
    public ResponseEntity<Local> add (@RequestBody Local local){

        //si c'est une creation
        if(local.getId()==null){
            localDao.save(local);
            return new ResponseEntity<>(local, HttpStatus.CREATED);
        }

        //si c'est une modif
        if(localDao.existsById(local.getId())){
            localDao.save(local);
            return new ResponseEntity<>(local, HttpStatus.OK);
        }

        //si l'utilisateur tante de modif un enregistrement qui n'existe pas/plus
        return new ResponseEntity<>(local, HttpStatus.BAD_REQUEST);
    }

}
