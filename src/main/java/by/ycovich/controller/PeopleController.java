package by.ycovich.controller;


import by.ycovich.dao.PersonDAO;
import by.ycovich.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("person") Person person){
        return "people/newbie";
    }
    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping()
    public String getUsers(Model model){
        model.addAttribute("people", personDAO.getPeople());
        return "people/all";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/person";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.getPerson(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") int id){
        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }


}
