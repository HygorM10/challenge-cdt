package com.hygor.conductor.services.exceptions;

public class LojaExistenteException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3256587914273718457L;

	public LojaExistenteException(String mensagem) {
		super(mensagem);
	}
	
	public LojaExistenteException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
