package com.example.igreja.errorHandle;

import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Problema {
	
	private OffsetDateTime hotaOcorrida;
	private Integer status;
	private String titulo;
	private String detalhes;
	private String tipo;
	private List<Campo> campos;
	
	@Getter
	@Builder
	public static class Campo {
		
		private String field;
		private String mensagem;
	}
}
