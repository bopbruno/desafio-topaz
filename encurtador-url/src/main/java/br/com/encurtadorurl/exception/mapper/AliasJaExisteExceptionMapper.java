package br.com.encurtadorurl.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.encurtadorurl.dto.ErroResponse;
import br.com.encurtadorurl.exception.AliasJaExisteException;

@Provider
public class AliasJaExisteExceptionMapper implements ExceptionMapper<AliasJaExisteException> {

	@Override
	public Response toResponse(AliasJaExisteException exception) {
        ErroResponse error = new ErroResponse("ALIAS_JA_EXISTE", exception.getMessage());
        return Response.status(Response.Status.CONFLICT)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
	}

}
