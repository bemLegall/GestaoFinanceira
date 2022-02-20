package br.com.MeArrumaUmEmprego.Alurado.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Despesa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private BigDecimal valor = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	
	public Despesa() {
	}
	
	public Despesa(String descricao,  BigDecimal valor) {
		this.descricao = descricao;
		this.valor = valor;
	}

	public Despesa(String descricao, BigDecimal valor, Categoria categoria) {
		this.descricao = descricao;
		this.valor = valor;
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public Long getId() {
		return id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
}
