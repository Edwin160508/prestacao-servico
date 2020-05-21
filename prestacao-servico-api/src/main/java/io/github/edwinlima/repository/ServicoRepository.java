package io.github.edwinlima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.edwinlima.model.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

}
