package br.com.encurtadorurl.exception;

public class URLNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public URLNaoEncontradaException(String codigo) {
		super("Nenhuma URL encontrada para o codigo '" + codigo + "'.");
	}

}
