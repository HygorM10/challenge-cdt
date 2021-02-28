package com.hygor.conductor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hygor.conductor.domain.Loja;

public interface LojaRepository extends JpaRepository<Loja, Long>{
	public Loja findByNome(String nome); 
}