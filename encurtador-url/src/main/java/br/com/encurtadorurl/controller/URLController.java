package br.com.encurtadorurl.controller;

import java.net.URI;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.encurtadorurl.dto.URLEncurtadaRequest;
import br.com.encurtadorurl.dto.URLEncurtadaResponse;
import br.com.encurtadorurl.service.URLService;


@Path("/url")
public class URLController {

	@Inject
	private URLService urlService;
	
    @Context
    private HttpServletRequest httpServletRequest;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(URLEncurtadaRequest urlEncurtadaRequest) {

    	URLEncurtadaResponse encurtadaResponse = urlService.encurtarURL(urlEncurtadaRequest);

    	String urlEncurtadaAbsoluta = this.constroiURLEncurtadaAbsoluta(encurtadaResponse.getUrlEncurtada());
    	
    	URLEncurtadaResponse urlEncurtadaAbsolutaResponse = new URLEncurtadaResponse(urlEncurtadaAbsoluta, urlEncurtadaRequest.getUrlOriginal());

        return Response.status(Response.Status.CREATED).entity(urlEncurtadaAbsolutaResponse).build();
    }

    @GET
    @Path("/{url-encurtada}")
    public Response redirect(@PathParam("url-encurtada") String urlEncurtada) {
        URLEncurtadaResponse originalUrl = urlService.retornarURLOriginal(urlEncurtada);
        return Response.status(Response.Status.FOUND)
                .location(URI.create(originalUrl.getUrlOriginal()))
                .build();
    }

    private String constroiURLEncurtadaAbsoluta(String urlEncurtada) {
        StringBuilder url = new StringBuilder();
        url.append(httpServletRequest.getScheme())
                .append("://")
                .append(httpServletRequest.getServerName());

        boolean isDefaultPort = (httpServletRequest.getScheme().equals("http") && httpServletRequest.getServerPort() == 80)
                || (httpServletRequest.getScheme().equals("https") && httpServletRequest.getServerPort() == 443);
        if (!isDefaultPort) {
            url.append(":").append(httpServletRequest.getServerPort());
        }

        url.append(httpServletRequest.getContextPath())
                .append("/api/url/")
                .append(urlEncurtada);

        return url.toString();
    }

}
