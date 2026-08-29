package br.com.encurtadorurl.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.encurtadorurl.dto.ErroResponse;
import br.com.encurtadorurl.exception.URLNaoEncontradaException;

@Provider
public class URLNaoEncontradaExceptionMapper implements ExceptionMapper<URLNaoEncontradaException> {

	@Override
	public Response toResponse(URLNaoEncontradaException exception) {
        ErroResponse error = new ErroResponse("URL_NOT_FOUND", exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
	}


}
