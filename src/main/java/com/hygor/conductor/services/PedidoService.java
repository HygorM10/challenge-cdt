package com.hygor.conductor.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygor.conductor.domain.ItemDePedido;
import com.hygor.conductor.domain.Loja;
import com.hygor.conductor.domain.Pagamento;
import com.hygor.conductor.domain.Pedido;
import com.hygor.conductor.domain.enums.StatusPagamento;
import com.hygor.conductor.domain.enums.StatusPedido;
import com.hygor.conductor.repository.ItemDePedidoRepository;
import com.hygor.conductor.repository.LojaRepository;
import com.hygor.conductor.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private LojaRepository lojaRepository;
	
	@Autowired
	private ItemDePedidoRepository itemDePedidoRepository;
	
	public List<Pedido> listar(){
		return pedidoRepository.findAll();
	}
	
	public Pedido buscarPedido(long id) throws Exception{
		Pedido pedido = pedidoRepository.findById(id).orElse(null);
		
		if(pedido == null)
			throw new Exception("Pedido não encontrado!");
		
		return pedido;
	}
	
	public Pedido salvar(Pedido pedido, long id_loja) throws Exception {
		if(pedido.getId() != null) {
			Pedido p = pedidoRepository.findById(pedido.getId()).orElse(null);
			
			if(p != null)
				throw new Exception("Pedido ja existe!");
		}
		
		Loja loja = lojaRepository.findById(id_loja).orElse(null);
		
		if(loja == null)
			throw new Exception("Loja não existe!");
		
		pedido.setLoja(loja);
		
		pedido.setStatus(StatusPedido.CONFIRMADO);
		
		return pedidoRepository.save(pedido);
	}
	
	public void salvarItensPedido(Pedido pedido) {
		
		for (ItemDePedido itemDePedido : pedido.getItemDePedido()) {
			itemDePedido.setPedido(pedido);
			itemDePedidoRepository.save(itemDePedido);
		}
		
	}
	
	public Pedido salvarPagamentoPedido(Pedido pedido) {
		return pedidoRepository.save(pedido);
	}
	
	public Pedido reembolsar(long id_loja, long id_pedido) throws Exception {
		Pedido pedido = pedidoRepository.findById(id_pedido).orElse(null);
		
		if(pedido == null)
			throw new Exception("Pedido não existe!");
		
		Loja loja = lojaRepository.findById(id_loja).orElse(null);
		
		if(loja == null || pedido.getLoja() != loja)
			throw new Exception("Loja não existe para o pedido!");
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		Date dataAtual = new Date(formatador.format(data));
		
		Date dataContratacao = new Date(formatador.format(pedido.getDataDeConfirmamacao()));
		dataContratacao.setDate(dataContratacao.getDate() + 10);
		
		if(pedido.getPagamento().getStatus() == StatusPagamento.CONCLUIDO && dataAtual.getDate() <= dataContratacao.getDate()) {
			pedido.getPagamento().setStatus(StatusPagamento.CANCELADO);
			pedido.setStatus(StatusPedido.CANCELADO);
		}else {
			throw new Exception("Pedido não confirmado ainda!");
		}
		
		return pedidoRepository.save(pedido);
	}
}
