package com.example.javaStatup.api;

import com.example.javaStatup.model.Person;
import com.example.javaStatup.service.PersonService;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")

@RestController
public class PersonController {
    private  final PersonService personService;
    @Autowired

    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public void addPerson(@Valid @NonNull @NotBlank @RequestBody Person person){
        personService.addPerson(person);
    }
    @GetMapping
    public List<Person> getAllPersons(){
       return personService.getPersons();
    }
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id).orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
        return personService.deletePersonById(id);
    }
    @PutMapping(path = "{id}")
    public int updatePersonById(@Valid @NonNull @NotBlank @PathVariable("id") UUID id, @RequestBody Person person){
        return personService.updatePersonByID(id,person);
    }


}
