package com.example.igreja.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IgrejaDTO {
	
	private Long id;
	private String nome;
	private OffsetDateTime dataCadastro;
	private EnderecoDTO endereco;
}
