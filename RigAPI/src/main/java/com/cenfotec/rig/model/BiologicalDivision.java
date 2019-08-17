package com.cenfotec.rig.model;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class BiologicalDivision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_bd;

	private String bdRegion;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_pd")
	private PoliticalDivision pd = new PoliticalDivision();

}
