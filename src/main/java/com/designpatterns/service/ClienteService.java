package com.designpatterns.service;

import com.designpatterns.model.Cliente;

/**
 * 
 * @author joanaleoni
 *
 */
public interface ClienteService {
	Iterable<Cliente> buscarTodos();
	Cliente buscarPorId(Long id);
	void inserir(Cliente cliente);
	void atualizar(Long id, Cliente cliente);
	void deletar(Long id);
}