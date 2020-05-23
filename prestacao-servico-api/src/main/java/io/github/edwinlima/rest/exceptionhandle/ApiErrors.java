package io.github.edwinlima.rest.exceptionhandle;

public class ApiErrors {

	private String mensagemUsuario;
	private String mensagemDesenvolvedor;
	
	public ApiErrors(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public String getMensagemUsuario() {
		return mensagemUsuario;
	}

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}
		
}
