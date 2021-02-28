package com.hygor.conductor.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.hygor.conductor.domain.Loja;
import com.hygor.conductor.domain.Pagamento;
import com.hygor.conductor.domain.Pedido;
import com.hygor.conductor.services.LojaService;
import com.hygor.conductor.services.PagamentoService;
import com.hygor.conductor.services.PedidoService;

@RestController
public class LojaResource {

	@Autowired
	private LojaService lojaService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PagamentoService pagamentoService;
	
	@GetMapping(value = "/lojas",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Loja>> listarLojas(){
		return ResponseEntity.status(HttpStatus.OK).body(lojaService.listar());
	}
	
	@PostMapping(value = "/lojas")
	public ResponseEntity<Void> salvarLoja(@RequestBody Loja loja){
		loja = lojaService.salvar(loja);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(loja.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping(value = "/lojas/{id_loja}")
	public ResponseEntity<Loja> buscarLoja(@PathVariable("id_loja") Long id_loja){
		return ResponseEntity.status(HttpStatus.OK).body(lojaService.buscar(id_loja));
	}
	
	@PutMapping(value = "/lojas/{id_loja}")
	public ResponseEntity<Void> atualizarLoja(@RequestBody Loja loja, @PathVariable("id_loja") Long id_loja){
		loja.setId(id_loja);
		lojaService.atualizar(loja);
		
		return ResponseEntity.noContent().build();
	}
	
	
//	Rotas Relacionadas aos pedidos da Loja
	
	@GetMapping(value = "/pedidos")
	public ResponseEntity<List<Pedido>> listar(){
		return ResponseEntity.ok(pedidoService.listar());
	}
	
	@PostMapping(value = "/lojas/{id_loja}/pedidos")
	public ResponseEntity<Void> salvarPedido(@PathVariable ("id_loja") Long id_loja, @RequestBody Pedido pedido) throws Exception{	
			
		pedido = pedidoService.salvar(pedido, id_loja);
		
		pedidoService.salvarItensPedido(pedido);
		
		Pagamento pagamento = pagamentoService.criarPagamento(pedido);
		
//		pedido.setPagamento(pagamento);
//		
//		pedido = pedidoService.salvarPagamentoPedido(pedido);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pedido.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/lojas/{id_loja}/pedidos/{id_pedido}/reembolso")
	public ResponseEntity<Void> reembolso(@PathVariable("id_loja") Long id_loja, @PathVariable("id_pedido") Long id_pedido) throws Exception{
		pedidoService.reembolsar(id_loja, id_pedido);
		
		return ResponseEntity.noContent().build();
	}
	
	
//	Rota de Pagamento
	
	@PutMapping(value = "/lojas/{id_loja}/pedidos/{id_pedido}/pagamentos")
	public ResponseEntity<Void> pagar(@PathVariable("id_loja") Long id_loja, @PathVariable("id_pedido") Long id_pedido, @RequestBody Pagamento pagamento) throws Exception{
		pagamento = pagamentoService.pagar(id_loja, id_pedido, pagamento);
		
		Pedido pedido = pedidoService.buscarPedido(id_pedido);
		
		pedido.setPagamento(pagamento);
		
		pedido = pedidoService.salvarPagamentoPedido(pedido);
		
		return ResponseEntity.noContent().build();
	}
}
