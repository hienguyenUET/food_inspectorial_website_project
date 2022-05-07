package com.example.food_inspectorate_website_project.repository;

import com.example.food_inspectorate_website_project.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
