package com.example.igreja.exception;

public class EntidadeNaoEncontrada extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public EntidadeNaoEncontrada(String mensagem) {
		super(mensagem);
	}

}
