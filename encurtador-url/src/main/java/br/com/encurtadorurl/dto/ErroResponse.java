package br.com.encurtadorurl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroResponse {
	
    private String code;
    private String message;

}
