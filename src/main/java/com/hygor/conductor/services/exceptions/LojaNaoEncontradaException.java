package com.hygor.conductor.services.exceptions;

public class LojaNaoEncontradaException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3850404861782400678L;

	public LojaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public LojaNaoEncontradaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
