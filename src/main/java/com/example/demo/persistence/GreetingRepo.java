package com.example.demo.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Greeting;

@Repository
public interface GreetingRepo extends JpaRepository<Greeting, Long>, RevisionRepository<Greeting, Long, Integer> {

	Optional<Greeting> findByGreetedUserSubject(String greetedUserSubject);
}
