package com.tomik.akzhol.FirstSerurityApp.services;

import com.tomik.akzhol.FirstSerurityApp.model.Person;
import com.tomik.akzhol.FirstSerurityApp.repositories.PeopleRepository;
import com.tomik.akzhol.FirstSerurityApp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailService implements UserDetailsService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PersonDetailService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = peopleRepository.findByUsername(username);
            if(person.isEmpty()){
                throw new UsernameNotFoundException("User not found!");
            }
            return new PersonDetails(person.get());
    }
}
