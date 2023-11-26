package com.example.javaStatup.dao;

import com.example.javaStatup.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao
{
 private  final List<Person> DB=new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id,person.getName()));
        return 1;
    }

    @Override
    public List<Person> selectAllPersons() {
        return DB;
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> FindPerson=selectPersonById(id);
        if(FindPerson.isEmpty()){
            return  0;
        }
        DB.remove(FindPerson.get());
            return 1;


    }

    @Override
    public int updatePersonById(UUID id, Person updatePerson) {
        return  selectPersonById(id).map(Oldperson -> {
            int indexOfPersonToDelete=DB.indexOf(Oldperson);
            if(indexOfPersonToDelete>=0){
                DB.set(indexOfPersonToDelete,new Person(id,updatePerson.getName()));
                return  1;
            }
            return  0;
        }).orElse(0);
    }


}
