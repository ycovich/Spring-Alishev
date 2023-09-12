package by.ycovich.dao;

import by.ycovich.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> getPeople(){
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    public Optional<Person> getPerson(String email){
        return jdbcTemplate.query("SELECT * FROM person WHERE email=?", new Object[] {email},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public Person getPerson(int id){
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?",
                        new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person){
        jdbcTemplate.update("INSERT INTO person(name, age, email, address) VALUES(?,?,?,?)",
                person.getName(), person.getAge(), person.getEmail(), person.getAddress());
    }

    public void update(int id, Person updPerson){
        jdbcTemplate.update("UPDATE person SET name=?, age=?, email=?, address=? WHERE id=?",
                updPerson.getName(), updPerson.getAge(), updPerson.getEmail(), updPerson.getAddress(), id);
    }

    public void delete(int id){
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
