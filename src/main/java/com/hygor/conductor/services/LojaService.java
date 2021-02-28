package com.hygor.conductor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygor.conductor.domain.Loja;
import com.hygor.conductor.repository.LojaRepository;
import com.hygor.conductor.services.exceptions.LojaExistenteException;
import com.hygor.conductor.services.exceptions.LojaNaoEncontradaException;

@Service
public class LojaService {

	@Autowired
	private LojaRepository lojaRepository;
	
	public List<Loja> listar(){
		return lojaRepository.findAll();
	}
	
	public Loja salvar(Loja loja) {
		if(loja.getId() != null) {
			Loja a = lojaRepository.findById(loja.getId()).orElse(null);
			
			if(a != null)
				throw new LojaExistenteException("Loja já existente");
		}
		
		return lojaRepository.save(loja);
	}
	
	public Loja buscar(Long id) {
		Loja loja = lojaRepository.findById(id).orElse(null);
		
		if(loja == null)
			throw new LojaNaoEncontradaException("Loja não foi encontrada!");
		
		return loja;
	}
	
	public void atualizar(Loja loja) {
		verificarExistenciaLoja(loja);
		lojaRepository.save(loja);
	}
	
	private void verificarExistenciaLoja(Loja loja) {
		buscar(loja.getId());
	}
}
