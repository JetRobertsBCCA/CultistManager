package com.example.demo.controller;

import com.example.demo.model.Cult;
import com.example.demo.repository.CultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cults")
public class CultController {

	@Autowired
	private CultRepository cultRepository;

	@GetMapping
	public List<Cult> getAllCults() {
		return cultRepository.findAll();
	}

	@PostMapping
	public Cult createCult(@RequestBody Cult cult) {
		return cultRepository.save(cult);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cult> getCultById(@PathVariable Long id) {
		return cultRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cult> updateCult(@PathVariable Long id, @RequestBody Cult cultDetails) {
		return cultRepository.findById(id)
				.map(cult -> {
					cult.setName(cultDetails.getName());
					return ResponseEntity.ok(cultRepository.save(cult));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCult(@PathVariable Long id) {
		return cultRepository.findById(id)
				.map(cult -> {
					cultRepository.delete(cult);
					return ResponseEntity.ok().<Void>build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
