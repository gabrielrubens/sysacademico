package com.prisila.controller;

import br.com.caelum.vraptor.Result;

import com.prisila.util.Mensagem;

public abstract class Controller {
	
	/**
	 * Método que centraliza o tratamento de mensagens para a tela
	 * 
	 * @param result
	 *            O objeto Result que receberá a mensagem
	 * @param mensagem
	 *            A mensagem para a tela
	 */
	protected void setMensagem(Result result, Mensagem mensagem) {
		result.include("mensagem", mensagem);
		System.out.printf("Registrando mensagem do tipo [%s]: %s%n", mensagem.getTipo(), mensagem.getMensagem());
	}
}