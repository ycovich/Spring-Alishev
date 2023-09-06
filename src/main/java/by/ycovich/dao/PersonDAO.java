package by.ycovich.dao;

import by.ycovich.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {

    private static int PEOPLE_CNT;
    private List<Person> people;

    {
        people = new ArrayList<>();

        people.add(new Person(++PEOPLE_CNT, "Larry"));
        people.add(new Person(++PEOPLE_CNT, "Bob"));
        people.add(new Person(++PEOPLE_CNT, "Paul"));
    }

    public List<Person> getPeople(){
        return people;
    }

    public Person getPerson(int id){
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person){
        person.setId(++PEOPLE_CNT);
        people.add(person);
    }

    public void update(int id, Person updPerson){
        Person personToBeUpd = getPerson(id);
        personToBeUpd.setName(updPerson.getName());
    }

    public void delete(int id){
        people.removeIf(p -> p.getId() == id);
    }
}
