package br.com.MeArrumaUmEmprego.Alurado.formulario;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.MeArrumaUmEmprego.Alurado.model.Categoria;
import br.com.MeArrumaUmEmprego.Alurado.model.Despesa;

public class DespesaFormulario {

	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private Categoria categoria = Categoria.OUTRAS;

	public DespesaFormulario() {
	}

	public DespesaFormulario(Despesa despesa) {
		despesa.setDescricao(descricao);
		despesa.setValor(valor);	
		despesa.setCategoria(categoria);
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

	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Despesa novaDespesa() {
		return new Despesa(descricao, valor);
	}

	public Despesa novaDespesaComCategoria() {
		return new Despesa(descricao, valor, categoria);
	}

}
