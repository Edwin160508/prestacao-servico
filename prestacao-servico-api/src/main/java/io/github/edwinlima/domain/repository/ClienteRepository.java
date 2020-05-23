package io.github.edwinlima.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.edwinlima.domain.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	public Cliente findByCpf(String cpf);
}
