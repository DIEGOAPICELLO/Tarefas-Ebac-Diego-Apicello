/**
 * 
 */
package br.com.mod35.services;

import br.com.mod35.domain.Cliente;
import br.com.mod35.exceptions.DAOException;
import br.com.mod35.services.generic.IGenericService;

public interface IClienteService extends IGenericService<Cliente, Long> {

//	Boolean cadastrar(Cliente cliente) throws TipoChaveNaoEncontradaException;
//
	Cliente buscarPorCPF(Long cpf) throws DAOException;
//
//	void excluir(Long cpf);
//
//	void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;

}
