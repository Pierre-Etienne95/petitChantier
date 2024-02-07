package edu.eval.PetiChantier.controller;

import edu.eval.PetiChantier.dao.SiteDao;
import edu.eval.PetiChantier.model.Site;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class SiteController {

    @Autowired
    protected SiteDao siteDao;

    @GetMapping("/site/{id}")
    public ResponseEntity<Site> get(@PathVariable int id){

        Optional<Site> optionalSite = siteDao.findById(id);

        if (optionalSite.isPresent()){
            return new ResponseEntity<>(optionalSite.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/sites")
    public List<Site> getAll(){


        List <Site> listeSite = siteDao.findAll();

        return listeSite;
    }

    @DeleteMapping("/site/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id){

        if(siteDao.existsById(id)) {

            siteDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/site")
    public ResponseEntity<Site> add (@RequestBody Site site){

        //si c'est une creation
        if(site.getId()==null){
            siteDao.save(site);
            return new ResponseEntity<>(site, HttpStatus.CREATED);
        }

        //si c'est une modif
        if(siteDao.existsById(site.getId())){
            siteDao.save(site);
            return new ResponseEntity<>(site, HttpStatus.OK);
        }

        //si l'utilisateur tante de modif un enregistrement qui n'existe pas/plus
        return new ResponseEntity<>(site, HttpStatus.BAD_REQUEST);
    }

}
