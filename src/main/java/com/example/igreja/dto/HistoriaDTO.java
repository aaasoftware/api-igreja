package com.example.igreja.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HistoriaDTO {
		
	private Long id;
	private String descricao;
	
	private IgrejaRelacionalDTO igreja;
	private PastoralRelacionalDTO pastoral;
}
