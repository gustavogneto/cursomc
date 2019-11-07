package com.gg.cursomc.domain;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date instante;
	private Pagamento pagamento;
	private Endereco enderecoEntrega;

	public Pedido() {

	}

	public Pedido(Integer id, Date instante, Pagamento pagamento, Endereco enderecoEntrega) {
		super();
		this.id = id;
		this.instante = instante;
		this.pagamento = pagamento;
		this.enderecoEntrega = enderecoEntrega;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", instante=" + instante + ", pagamento=" + pagamento + ", enderecoEntrega="
				+ enderecoEntrega + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public Date getInstante() {
		return instante;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void setEnderecoEntrega(Endereco enderecoEntrega) {
		this.enderecoEntrega = enderecoEntrega;
	}

}
