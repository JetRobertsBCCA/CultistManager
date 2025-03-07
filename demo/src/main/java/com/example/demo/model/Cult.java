package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cult {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "cult", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<Cultist> cultists = new HashSet<>();

	public Long getId() {
		return id;
	}

	@OneToMany(mappedBy = "cult", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<CultMembership> memberships = new HashSet<>();

	public Set<CultMembership> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<CultMembership> memberships) {
		this.memberships = memberships;
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

	public Set<Cultist> getCultists() {
		return cultists;
	}

	public void setCultists(Set<Cultist> cultists) {
		this.cultists = cultists;
	}
}
