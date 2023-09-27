package com.massafra.club.repositories;

import com.massafra.club.entities.Orcamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, String> {
    Optional<Orcamento> findByNumeroAndEmpresa(String numero, String empresa);
}
