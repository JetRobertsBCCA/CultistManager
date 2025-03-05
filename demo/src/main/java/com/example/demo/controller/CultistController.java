package com.example.demo.controller;

import com.example.demo.model.Cultist;
import com.example.demo.repository.CultistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cultists")
public class CultistController {

	@Autowired
	private CultistRepository cultistRepository;

	@GetMapping
	public List<Cultist> getAllCultists() {
		return cultistRepository.findAll();
	}

	@PostMapping
	public Cultist createCultist(@RequestBody Cultist cultist) {
		return cultistRepository.save(cultist);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cultist> getCultistById(@PathVariable Long id) {
		return cultistRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cultist> updateCultist(@PathVariable Long id, @RequestBody Cultist cultistDetails) {
		return cultistRepository.findById(id)
				.map(cultist -> {
					cultist.setName(cultistDetails.getName());
					cultist.setCult(cultistDetails.getCult());
					return ResponseEntity.ok(cultistRepository.save(cultist));
				})
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCultist(@PathVariable Long id) {
		return cultistRepository.findById(id)
				.map(cultist -> {
					cultistRepository.delete(cultist);
					return ResponseEntity.ok().<Void>build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
