package com.cenfotec.rig.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PoliticalDivision {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_pd;

	private String pdName;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rig")
	private Rig rig = new Rig();

}
