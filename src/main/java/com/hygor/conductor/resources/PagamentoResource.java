package com.hygor.conductor.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hygor.conductor.domain.Pagamento;
import com.hygor.conductor.services.PagamentoService;

@RestController
@RequestMapping(value = "/pagamentos")
public class PagamentoResource {

//	@Autowired
//	private PagamentoService pagamentoService;
//	
//	@PostMapping
//	public ResponseEntity<Void> salvar(@RequestBody Pagamento pagamento) throws Exception{
//		pagamento = pagamentoService.pagar(pagamento);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(pagamento.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//	}
}
