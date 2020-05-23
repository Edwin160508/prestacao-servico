package io.github.edwinlima.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.edwinlima.domain.entity.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer>{

}
