package br.com.MeArrumaUmEmprego.Alurado.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.com.MeArrumaUmEmprego.Alurado.model.Receita;

public class ReceitaDtoCompleto {

	private String descricao;
	private BigDecimal valor;
	private LocalDate data;

	public ReceitaDtoCompleto(Receita receita) {
		this.descricao = receita.getDescricao();
		this.valor = receita.getValor();
		this.data = receita.getData();
		
	}

	public String getDescricao() {
		return descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public LocalDate getData() {
		return data;
	}
	
	public static List<ReceitaDtoCompleto> converteToList(List<Receita>receita) {
		return receita.stream().map(ReceitaDtoCompleto::new).collect(Collectors.toList());
	}
	public static ReceitaDtoCompleto converter( Receita receita) {
		return new ReceitaDtoCompleto(receita);
	}
	
}
