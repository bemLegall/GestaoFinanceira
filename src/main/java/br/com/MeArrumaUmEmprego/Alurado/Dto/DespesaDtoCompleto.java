package br.com.MeArrumaUmEmprego.Alurado.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.MeArrumaUmEmprego.Alurado.model.Categoria;
import br.com.MeArrumaUmEmprego.Alurado.model.Despesa;

public class DespesaDtoCompleto {

	private String descricao;
	private BigDecimal valor;
	private LocalDate data;
	private Categoria categoria;

	public DespesaDtoCompleto(Despesa despesa) {
		this.data = despesa.getData();
		this.descricao = despesa.getDescricao();
		this.valor = despesa.getValor();
		this.categoria = despesa.getCategoria();
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDate getData() {
		return data;
	}

	public static DespesaDtoCompleto converterSimples(Despesa despesa) {
		return new DespesaDtoCompleto(despesa);
	}

	public static List<DespesaDtoCompleto> converterLista(List<Despesa> despesas) {
		return despesas.stream().map(DespesaDtoCompleto::new).collect(Collectors.toList());
	}
}
