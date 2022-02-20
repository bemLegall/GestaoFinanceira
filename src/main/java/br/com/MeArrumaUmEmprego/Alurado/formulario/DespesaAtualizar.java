package br.com.MeArrumaUmEmprego.Alurado.formulario;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.MeArrumaUmEmprego.Alurado.Repository.DespesaRepository;
import br.com.MeArrumaUmEmprego.Alurado.model.Categoria;
import br.com.MeArrumaUmEmprego.Alurado.model.Despesa;

public class DespesaAtualizar {

	private String descricao;
	private BigDecimal valor;
	private LocalDate data = LocalDate.now();
	private Categoria categoria;

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Despesa despesaAtualizar(Long id, DespesaRepository despesaRepository) {
		Despesa despesa = despesaRepository.getById(id);
		despesa.setData(data);
		despesa.setDescricao(descricao);
		despesa.setValor(valor);
		despesa.setCategoria(categoria);
		return despesa;
		
	}
	
}
