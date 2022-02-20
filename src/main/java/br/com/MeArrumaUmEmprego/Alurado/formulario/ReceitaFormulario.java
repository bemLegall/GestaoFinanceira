package br.com.MeArrumaUmEmprego.Alurado.formulario;


import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.MeArrumaUmEmprego.Alurado.model.Receita;

public class ReceitaFormulario {

	@NotBlank
	public String descricao;
	@NotNull
	public BigDecimal valor;

	public ReceitaFormulario() {
		// TODO Auto-generated constructor stub
	}
	
	public ReceitaFormulario(Receita receita) {
		
		receita.setDescricao(descricao);
		receita.setValor(valor);
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
	
	public Receita novaReceita() {
		return new Receita(descricao, valor);
	}
	
}
