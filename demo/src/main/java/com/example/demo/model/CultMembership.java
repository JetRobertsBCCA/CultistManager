package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class CultMembership {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "cult_id")
	private Cult cult;

	@ManyToOne
	@JoinColumn(name = "cultist_id")
	private Cultist cultist;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cult getCult() {
		return cult;
	}

	public void setCult(Cult cult) {
		this.cult = cult;
	}

	public Cultist getCultist() {
		return cultist;
	}

	public void setCultist(Cultist cultist) {
		this.cultist = cultist;
	}
}
