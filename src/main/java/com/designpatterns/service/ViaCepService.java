package com.designpatterns.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.designpatterns.model.Endereco;

/**
 * Client HTTP, criado via OpenFeign, para o consumo da API do ViaCEP.
 * 
 * @see <a href="https://spring.io/projects/spring-cloud-openfeign">Spring Cloud OpenFeing</a>
 * @see <a href="https://viacep.com.br">ViaCEP</a> 
 * 
 * @author joanaleoni
 *
 */
@FeignClient(name="viacep", url="https://viacep.com.br/ws")
public interface ViaCepService {
	
	@GetMapping("/{cep}/json")
	Endereco consultarCep(@PathVariable("cep") String cep);
}