package com.example.igreja.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {
	
	@NotNull
	private String cep;
	
	@NotNull
	private String cidade;
	
	private String logradouro;
	private String bairro;
	private String estado;
}
