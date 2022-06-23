package com.meloeclaranhan.prefeitura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meloeclaranhan.prefeitura.modelo.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
