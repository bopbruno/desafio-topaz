package br.com.encurtadorurl.service.impl;

import java.util.Optional;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.encurtadorurl.domain.URL;
import br.com.encurtadorurl.dto.URLEncurtadaRequest;
import br.com.encurtadorurl.dto.URLEncurtadaResponse;
import br.com.encurtadorurl.exception.AliasJaExisteException;
import br.com.encurtadorurl.exception.URLNaoEncontradaException;
import br.com.encurtadorurl.repository.UrlRepository;
import br.com.encurtadorurl.service.URLService;

@Stateless
public class URLServiceImpl implements URLService {

    private static final Object CODE_GENERATION_LOCK = new Object();

	@Inject
	private UrlRepository urlRepository;

	@Override
	public URLEncurtadaResponse encurtarURL(URLEncurtadaRequest urlEncurtadaRequest) {

		boolean gerarCodigoUrlEncurtada = urlEncurtadaRequest.getAlias() == null || urlEncurtadaRequest.getAlias().trim().isEmpty();

        synchronized (CODE_GENERATION_LOCK) {

    		String urlEncurt = gerarCodigoUrlEncurtada ? String.valueOf(urlRepository.proximoValorSequence()) : urlEncurtadaRequest.getAlias();

    		URL url = new URL(urlEncurt, urlEncurtadaRequest.getUrlOriginal());

    		if(urlRepository.findByURLEncurtada(urlEncurt).isPresent())
    			throw new AliasJaExisteException(urlEncurt);

    		urlRepository.save(url);

    		String urlBase = System.getenv("APP_BASE_URL") != null ? System.getenv("APP_BASE_URL") : "http://localhost:8080/encurtador-url/api/url/";

    		URLEncurtadaResponse urlEncurtadaResponse = new URLEncurtadaResponse(urlBase + urlEncurt, urlEncurtadaRequest.getUrlOriginal());

    		return (urlEncurtadaResponse);
        }

	}

	@Override
	public URLEncurtadaResponse retornarURLOriginal(String urlEncurtada) {

		Optional<URL> urlOriginalOpt = urlRepository.findByURLEncurtada(urlEncurtada);

		if(urlOriginalOpt.isPresent())
			return new URLEncurtadaResponse(urlEncurtada, urlOriginalOpt.get().getUrlOriginal());
		else
			throw new URLNaoEncontradaException(urlEncurtada);

	}

}
