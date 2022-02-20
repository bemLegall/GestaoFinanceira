package br.com.MeArrumaUmEmprego.Alurado.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.MeArrumaUmEmprego.Alurado.model.Despesa;
import br.com.MeArrumaUmEmprego.Alurado.model.Receita;

public class ResumoDto {

	private BigDecimal receita = BigDecimal.ZERO;
	private BigDecimal despesa = BigDecimal.ZERO;
	private BigDecimal saldo = BigDecimal.ZERO;
	private BigDecimal valorTotalGasto = BigDecimal.ZERO;
	private LocalDate data = LocalDate.now();
	

	public ResumoDto(Receita receita, Despesa despesa) {
		this.data = receita.getData();
		this.receita = receita.getValor();
		this.despesa = despesa.getValor();
		this.saldo = this.despesa.subtract(this.receita);

	}

	public BigDecimal getDespesa() {
		return despesa;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public BigDecimal getValorTotalGasto() {
		return valorTotalGasto;
	}

	public BigDecimal getReceita() {
		return receita;
	}

	public void setReceita(BigDecimal valor) {
		this.receita = valor;
	}

	public LocalDate getData() {
		return data;
	}


	public static ResumoDto converter(Receita receita, Despesa despesa) {
		return new ResumoDto(receita, despesa);
	}
}
