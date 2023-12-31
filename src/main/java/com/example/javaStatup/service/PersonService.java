package com.example.javaStatup.service;

import com.example.javaStatup.dao.PersonDao;
import com.example.javaStatup.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service

public class PersonService {
    private final PersonDao personDao;
    @Autowired

    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public  int addPerson(Person person){
        return personDao.insertPerson(person);
    }
    public List<Person> getPersons(){
        return  personDao.selectAllPersons();
    }
    public Optional<Person> getPersonById(UUID id){
        return  personDao.selectPersonById(id);
    }
    public int deletePersonById(UUID id){
        return  personDao.deletePersonById(id);
    }
    public int updatePersonByID(UUID id,Person person){
        return  personDao.updatePersonById(id,person);
    }
}
