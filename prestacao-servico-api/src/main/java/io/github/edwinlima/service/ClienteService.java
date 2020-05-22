package io.github.edwinlima.service;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.edwinlima.model.entity.Cliente;
import io.github.edwinlima.repository.ClienteRepository;
import io.github.edwinlima.rest.model.ClienteInput;
import io.github.edwinlima.rest.model.ClienteOutput;

@Service
public class ClienteService {

	
	private final ClienteRepository repository;
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public ClienteService (ClienteRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}
	
	public ClienteOutput salvar(ClienteInput cliente) {		
		validaClienteCpfRepetido(cliente);
		Cliente clienteSalvo = repository.save(toEntity(cliente));
		return toModel(clienteSalvo);
		
	}
	
	public ClienteOutput atualizar(Integer id,ClienteInput clienteInput) {	
		try {
			Optional<Cliente> clienteEncontrado = buscaClientePeloId(id);
			clienteEncontrado.map(cliente -> {
				cliente.setNome(clienteInput.getNome());
				cliente.setCpf(clienteInput.getCpf());
				return repository.save(cliente);
			});		
			return toModel(clienteEncontrado.get());
		}catch(NoSuchElementException i) {
			i.getMessage();
			// throw  new NegocioException("Cliente não encontrado.");
		}
		return null;
	}
	
	private void validaClienteCpfRepetido(ClienteInput cliente) {
		Cliente clienteEncontrado = repository.findByCpf(cliente.getCpf());
		if(Objects.nonNull(clienteEncontrado)) {
			// throw  new NegocioException("Já existe cliente cadastrado com este cpf");
		}
	}		
	
	public Optional<Cliente> buscaClientePeloId(Integer id) {
		return repository.findById(id);
	}
	
	public ClienteOutput toModel(Cliente entity) {
		return modelMapper.map(entity, ClienteOutput.class);
	}
	
	private Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}
}
