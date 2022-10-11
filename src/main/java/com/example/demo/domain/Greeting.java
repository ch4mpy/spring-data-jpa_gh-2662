package com.example.demo.domain;

import org.hibernate.envers.Audited;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Audited
@Data
@NoArgsConstructor
public class Greeting {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String greetedUserSubject;

	@Column(nullable = false)
	private String message;

	public Greeting(String greetedUserSubject, String message) {
		super();
		this.greetedUserSubject = greetedUserSubject;
		this.message = message;
	}
}
