package com.hygor.conductor.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hygor.conductor.domain.Pedido;
import com.hygor.conductor.services.LojaService;
import com.hygor.conductor.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

//	@Autowired
//	private PedidoService pedidoService;
//	
//	@Autowired
//	private LojaService lojaService;
//	
//	@GetMapping
//	public ResponseEntity<List<Pedido>> listar(){
//		return ResponseEntity.ok(pedidoService.listar());
//	}
//	
//	@PostMapping
//	public ResponseEntity<Void> salvar(@RequestBody Pedido pedido) throws Exception{	
//			
//		pedido = pedidoService.salvar(pedido);
//		
//		pedidoService.salvarItensPedido(pedido);
//		
//		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
//				.path("/{id}").buildAndExpand(pedido.getId()).toUri();
//		
//		return ResponseEntity.created(uri).build();
//	}
//	
//	@PutMapping(value = "/{id_pedido}")
//	public ResponseEntity<Void> reembolso(@PathVariable("id_pedido") Long id_pedido) throws Exception{
//		pedidoService.reembolsar(id_pedido);
//		
//		return ResponseEntity.noContent().build();
//	}
}
