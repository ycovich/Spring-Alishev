package by.ycovich.util;

import by.ycovich.dao.PersonDAO;
import by.ycovich.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> targetPerson = personDAO.getPerson(person.getEmail());
        if (targetPerson.isPresent() && targetPerson.get().getId() != person.getId()){
            errors.rejectValue("email", "", "this email is already taken");
        }
    }
}
