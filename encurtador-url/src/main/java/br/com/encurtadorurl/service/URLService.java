package br.com.encurtadorurl.service;

import br.com.encurtadorurl.dto.URLEncurtadaRequest;
import br.com.encurtadorurl.dto.URLEncurtadaResponse;

public interface URLService {

	URLEncurtadaResponse encurtarURL(URLEncurtadaRequest urlEncurtadaRequest);

	URLEncurtadaResponse retornarURLOriginal(String urlEncurtada);

}
