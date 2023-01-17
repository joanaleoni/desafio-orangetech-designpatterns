package com.designpatterns.repository;

import org.springframework.data.repository.CrudRepository;

import com.designpatterns.model.Endereco;

/**
 * 
 * @author joanaleoni
 *
 */
public interface EnderecoRepository extends CrudRepository<Endereco, String>{

}