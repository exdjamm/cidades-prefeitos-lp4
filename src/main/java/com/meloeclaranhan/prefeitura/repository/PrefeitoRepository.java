package com.meloeclaranhan.prefeitura.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meloeclaranhan.prefeitura.modelo.Prefeito;

public interface PrefeitoRepository extends JpaRepository<Prefeito, Long> {

}
