package com.example.igreja.dto;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisoDTO {
		
	private String descricao;
	private OffsetDateTime data;
	private IgrejaRelacionalDTO igreja;
	private PastoralRelacionalDTO pastoral;
}
