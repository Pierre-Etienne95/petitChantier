package edu.eval.PetiChantier.security;

import edu.eval.PetiChantier.dao.PersonneDao;
import edu.eval.PetiChantier.model.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

    @Autowired
    PersonneDao personneDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Personne> optionalPersonne  = personneDao.findByEmail(email);

        if (optionalPersonne.isPresent()) {
            return new User(optionalPersonne.get());
        }

        throw new UsernameNotFoundException("Email introuvable");

    }

}
