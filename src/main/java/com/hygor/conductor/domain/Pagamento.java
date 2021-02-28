package com.hygor.conductor.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hygor.conductor.domain.enums.StatusPagamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Pagamento {

	public Pagamento(Pedido pedido) {
		this.pedido = pedido;
		this.setStatus(StatusPagamento.EM_ABERTO);
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	private StatusPagamento status;

	private String numeroCartaoCredito;

	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataPagamento;

	@OneToOne
	@JoinColumn(name = "id_pedido")
	@JsonIgnore
	private Pedido pedido;
}
