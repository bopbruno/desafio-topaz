package br.com.encurtadorurl.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.encurtadorurl.domain.URL;
import br.com.encurtadorurl.dto.URLEncurtadaRequest;
import br.com.encurtadorurl.dto.URLEncurtadaResponse;
import br.com.encurtadorurl.repository.UrlRepository;

@RunWith(MockitoJUnitRunner.class)
public class URLServiceImplTest {

    @Mock
    private UrlRepository urlRepository;

    @InjectMocks
    private URLServiceImpl urlService;

    private URLEncurtadaRequest request;

    @Before
    public void setUp() {
        request = new URLEncurtadaRequest();
        request.setUrlOriginal("https://www.exemplo.com/testando");
    }


    @Test
    public void encurtarURL_semAlias_deveGerarCodigoAPartirDaSequence() {

        when(urlRepository.proximoValorSequence()).thenReturn(42L);
        when(urlRepository.findByURLEncurtada("42")).thenReturn(Optional.empty());

        URLEncurtadaResponse response = urlService.encurtarURL(request);

        assertEquals("http://localhost:8080/encurtador-url/api/url/42", response.getUrlEncurtada());
        assertEquals(request.getUrlOriginal(), response.getUrlOriginal());

    }

    @Test
    public void encurtarURL_comAliasInformado_deveUsarAliasEmVezDoCodigoGerado() {
        request.setAlias("meu-link");

        when(urlRepository.findByURLEncurtada("meu-link")).thenReturn(Optional.empty());

        URLEncurtadaResponse response = urlService.encurtarURL(request);

        assertEquals("http://localhost:8080/encurtador-url/api/url/meu-link", response.getUrlEncurtada());
        assertEquals(request.getUrlOriginal(), response.getUrlOriginal());

        verify(urlRepository).save(any(URL.class));
    }


    @Test
    public void retornarURLOriginal_quandoEncontrada_deveRetornarResponseComUrlOriginal() {
        URL urlExistente = new URL("abc123", "https://destino-original.com");
        when(urlRepository.findByURLEncurtada("abc123")).thenReturn(Optional.of(urlExistente));

        URLEncurtadaResponse response = urlService.retornarURLOriginal("abc123");

        assertEquals("abc123", response.getUrlEncurtada());
        assertEquals("https://destino-original.com", response.getUrlOriginal());
    }

}