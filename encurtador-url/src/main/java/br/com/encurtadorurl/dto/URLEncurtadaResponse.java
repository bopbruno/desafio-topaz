package br.com.encurtadorurl.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class URLEncurtadaResponse {

    private String urlEncurtada;
    private String urlOriginal;

}
