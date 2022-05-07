package com.example.food_inspectorate_website_project.service.implementation;

import com.example.food_inspectorate_website_project.entity.Person;
import com.example.food_inspectorate_website_project.repository.PersonRepository;
import com.example.food_inspectorate_website_project.service.service.PersonService;
import org.springframework.stereotype.Service;

@Service

public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    public PersonServiceImpl(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Person person) {
        repository.save(person);
    }
}
