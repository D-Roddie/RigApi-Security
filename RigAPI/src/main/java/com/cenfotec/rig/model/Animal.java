package com.cenfotec.rig.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Animal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_animal;
	
	private String animalName;
	private String scientificName;
	private String danger;
	private String population;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_bd")
	private BiologicalDivision bd = new BiologicalDivision();

}
