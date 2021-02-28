package com.hygor.conductor.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hygor.conductor.domain.Pagamento;
import com.hygor.conductor.domain.Pedido;
import com.hygor.conductor.domain.enums.StatusPagamento;
import com.hygor.conductor.repository.PagamentoRepository;
import com.hygor.conductor.repository.PedidoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pagamento pagar(long id_loja, long id_pedido, Pagamento pagamento) throws Exception {
		Pedido pedido = pedidoRepository.findById(id_pedido).orElse(null);
		
		if(pedido != null && pedido.getPagamento().getStatus() == StatusPagamento.CONCLUIDO)
			throw new Exception("Pedido já esta pago");
		
		pedido.getPagamento().setDataPagamento(new Date());
		pedido.getPagamento().setStatus(StatusPagamento.CONCLUIDO);
		pedido.getPagamento().setNumeroCartaoCredito(pagamento.getNumeroCartaoCredito());
		return pagamentoRepository.save(pedido.getPagamento());
		
	}
	
	public Pagamento criarPagamento(Pedido pedido) {
		Pagamento pagamento = new Pagamento(pedido);
		
		return pagamentoRepository.save(pagamento);
	}
}
