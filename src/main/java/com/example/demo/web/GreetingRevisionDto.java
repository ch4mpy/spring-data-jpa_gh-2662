package com.example.demo.web;

import java.io.Serializable;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GreetingRevisionDto implements Serializable {
	private static final long serialVersionUID = 7556861206524029179L;

	private Integer revision;

	@NotEmpty
	private String message;
}
