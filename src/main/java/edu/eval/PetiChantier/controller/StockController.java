package edu.eval.PetiChantier.controller;

import edu.eval.PetiChantier.dao.StockDao;
import edu.eval.PetiChantier.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class StockController {

    @Autowired
    protected StockDao stockDao;

    @GetMapping("/stock/{id}")
    public ResponseEntity<Stock> get(@PathVariable int id){

        Optional<Stock> optionalStock = stockDao.findById(id);

        if (optionalStock.isPresent()){
            return new ResponseEntity<>(optionalStock.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/stocks")
    public List<Stock> getAll(){


        List <Stock> listeStock = stockDao.findAll();

        return listeStock;
    }

    @DeleteMapping("/stock/{id}")
    public ResponseEntity<Integer> delete(@PathVariable int id){

        if(stockDao.existsById(id)) {

            stockDao.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }
        return new ResponseEntity<>(id,HttpStatus.NOT_FOUND);
    }

    @PostMapping("/stock")
    public ResponseEntity<Stock> add (@RequestBody Stock stock){

        //si c'est une creation
        if(stock.getId()==null){
            stockDao.save(stock);
            return new ResponseEntity<>(stock, HttpStatus.CREATED);
        }

        //si c'est une modif
        if(stockDao.existsById(stock.getId())){
            stockDao.save(stock);
            return new ResponseEntity<>(stock, HttpStatus.OK);
        }

        //si l'utilisateur tante de modif un enregistrement qui n'existe pas/plus
        return new ResponseEntity<>(stock, HttpStatus.BAD_REQUEST);
    }

}
