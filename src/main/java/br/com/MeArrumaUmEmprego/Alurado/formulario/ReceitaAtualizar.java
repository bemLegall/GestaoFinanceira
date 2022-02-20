package br.com.MeArrumaUmEmprego.Alurado.formulario;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.MeArrumaUmEmprego.Alurado.Repository.ReceitaRepository;
import br.com.MeArrumaUmEmprego.Alurado.model.Receita;

public class ReceitaAtualizar {

	@NotBlank
	private String descricao;
	@NotNull
	private BigDecimal valor;
	private LocalDate data = LocalDate.now();
	
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
	
	public Receita atualizar (Long id,ReceitaRepository recRepository) {
		Receita receita = recRepository.getById(id);
		receita.setData(this.data);
		receita.setDescricao(this.descricao);
		receita.setValor(this.valor);
		return receita;
		
		
	}
	
}
