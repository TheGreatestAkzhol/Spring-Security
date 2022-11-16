package com.tomik.akzhol.FirstSerurityApp.util;

import com.tomik.akzhol.FirstSerurityApp.model.Person;
import com.tomik.akzhol.FirstSerurityApp.services.PersonDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class PersonValidator implements Validator{
    private final PersonDetailService personDetailService;
    @Autowired
    public PersonValidator(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person)target;
        try {
            personDetailService.loadUserByUsername(person.getUsername());
        }catch (UsernameNotFoundException ignored){
            return;//все ок, пользователь не найден
        }
        errors.rejectValue("username", "","Человек с таким именем пользователя уже существует");
        }
}
