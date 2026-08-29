package br.com.encurtadorurl.repository;

import java.util.Optional;

import br.com.encurtadorurl.domain.URL;

public interface UrlRepository {

	URL save(URL url);

	Optional<URL> findByURLEncurtada(String urlEncurtada);

	long contarTotalURLs();
	
	long proximoValorSequence();

}
