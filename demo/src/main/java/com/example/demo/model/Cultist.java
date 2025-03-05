package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Cultist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "cult_id")
	private Cult cult;

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Cult getCult() {
		return cult;
	}

	public void setCult(Cult cult) {
		this.cult = cult;
	}
}
