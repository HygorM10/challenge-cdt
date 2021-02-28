package com.hygor.conductor.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hygor.conductor.domain.enums.StatusPedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String endereco;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dataDeConfirmamacao;
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pedido")
	private List<ItemDePedido> itemDePedido;
	
	@ManyToOne
	@JoinColumn(name = "id_loja")
	private Loja loja;
	
	@OneToOne(mappedBy = "pedido")
	private Pagamento pagamento;
}
