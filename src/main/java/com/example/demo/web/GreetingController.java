package com.example.demo.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Greeting;
import com.example.demo.persistence.GreetingRepo;

import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/greetings")
@Data
@RequiredArgsConstructor
public class GreetingController {
	private final GreetingRepo greetingRepo;

	@GetMapping("/{who}")
	public String greet(@PathVariable("who") String who) {
		return greetingRepo.findByGreetedUserSubject(who).map(Greeting::getMessage).orElse("Hello!");
	}

	@PutMapping("/{who}")
	public ResponseEntity<Void> updateGreetingMessage(@PathVariable("who") String who, @RequestBody @Valid UpdateGreetingMessageDto dto) {
		var greeting = greetingRepo.findByGreetedUserSubject(who).orElse(new Greeting(who, dto.getMessage()));
		greeting.setMessage(dto.getMessage());
		greeting = greetingRepo.save(greeting);
		return ResponseEntity.accepted().build();
	}

	@GetMapping("/{who}/revisions")
	public List<GreetingRevisionDto> getRevisions(@PathVariable("who") String who) {
		return greetingRepo.findByGreetedUserSubject(who).map(g -> greetingRepo.findRevisions(g.getId())).map(r -> r.getContent()).orElse(List.of()).stream()
				.map(r -> new GreetingRevisionDto(r.getRevisionNumber().orElse(null), r.getEntity().getMessage())).toList();
	}
}
