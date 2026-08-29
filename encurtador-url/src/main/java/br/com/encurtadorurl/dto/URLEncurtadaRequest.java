package br.com.encurtadorurl.dto;

import lombok.Data;

@Data
public class URLEncurtadaRequest {

	private String urlOriginal;
	private String alias;

}
