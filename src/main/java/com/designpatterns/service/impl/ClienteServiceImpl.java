package com.designpatterns.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.designpatterns.model.Cliente;
import com.designpatterns.model.Endereco;
import com.designpatterns.repository.ClienteRepository;
import com.designpatterns.repository.EnderecoRepository;
import com.designpatterns.service.ClienteService;
import com.designpatterns.service.ViaCepService;

/**
 * 
 * @author joanaleoni
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ViaCepService viaCepService;
	
	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}
	
	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarClienteComCep(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		Optional<Cliente> clienteBd = clienteRepository.findById(id);
		if(clienteBd.isPresent()) {
			salvarClienteComCep(cliente);
		}		
	}
	
	private void salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});		
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);		
	}
}