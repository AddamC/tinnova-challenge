package com.tinnova.challenge.api.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="veiculo")
@ToString
public class Veiculo {
	
	@Getter @Setter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Getter @Setter
	@NotNull
	private String nome;

	@Getter @Setter
	@NotNull
	private String marca;

	@Getter @Setter
	@NotNull
	private int ano;

	@Getter @Setter
	@NotNull
	private String descricao;

	@Getter @Setter
	@NotNull
	private boolean vendido;

	@Getter @Setter
	@NotNull
	private Date created;
	
	@Getter @Setter
	@NotNull
	private Date updated;
}
