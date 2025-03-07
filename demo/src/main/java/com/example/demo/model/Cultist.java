package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Cultist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "cult_id")
	@JsonBackReference
	private Cult cult;

	@OneToMany(mappedBy = "cultist", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CultMembership> memberships = new HashSet<>();

	public Set<CultMembership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<CultMembership> memberships) {
		this.memberships = memberships;
	}


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
