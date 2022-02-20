package br.com.MeArrumaUmEmprego.Alurado.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Resumo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate data = LocalDate.now();
	private BigDecimal receitaDoMes = BigDecimal.ZERO;
	private BigDecimal despesaDoMes = BigDecimal.ZERO;
	private BigDecimal saldoDoMes = BigDecimal.ZERO;
	private BigDecimal gastosComLazer = BigDecimal.ZERO;
	private BigDecimal gastosComAlimentacao = BigDecimal.ZERO;
	private BigDecimal gastosComOutros = BigDecimal.ZERO;
	private BigDecimal gastosComMoradia = BigDecimal.ZERO;
	private BigDecimal gastosComEducacao = BigDecimal.ZERO;
	private BigDecimal gastosComTransporte = BigDecimal.ZERO;
	private BigDecimal gastosComImprevistos = BigDecimal.ZERO;
	

	
	
	public void setGastosComLazer(BigDecimal gastosComLazer) {
		this.gastosComLazer = gastosComLazer;
	}



	public void setGastosComAlimentacao(BigDecimal gastosComAlimentacao) {
		this.gastosComAlimentacao = gastosComAlimentacao;
	}



	public void setGastosComOutros(BigDecimal gastosComOutros) {
		this.gastosComOutros = gastosComOutros;
	}



	public void setGastosComMoradia(BigDecimal gastosComMoradia) {
		this.gastosComMoradia = gastosComMoradia;
	}



	public void setGastosComEducacao(BigDecimal gastosComEducacao) {
		this.gastosComEducacao = gastosComEducacao;
	}



	public void setGastosComTransporte(BigDecimal gastosComTransporte) {
		this.gastosComTransporte = gastosComTransporte;
	}



	public void setGastosComImprevistos(BigDecimal gastosComImprevistos) {
		this.gastosComImprevistos = gastosComImprevistos;
	}



	public BigDecimal getGastosComLazer() {
		return gastosComLazer;
	}



	public BigDecimal getGastosComAlimentacao() {
		return gastosComAlimentacao;
	}



	public BigDecimal getGastosComOutros() {
		return gastosComOutros;
	}



	public BigDecimal getGastosComMoradia() {
		return gastosComMoradia;
	}



	public BigDecimal getGastosComEducacao() {
		return gastosComEducacao;
	}



	public BigDecimal getGastosComTransporte() {
		return gastosComTransporte;
	}



	public BigDecimal getGastosComImprevistos() {
		return gastosComImprevistos;
	}



	public void setDespesaDoMes(BigDecimal despesaDoMes) {
		this.despesaDoMes = despesaDoMes;
	}

	

	public Resumo() {
	}

	public Resumo(BigDecimal receitaDoMes, BigDecimal despesaDoMes, BigDecimal saldoDoMes) {
		this.data = LocalDate.now();
		this.receitaDoMes = receitaDoMes;
		this.despesaDoMes = despesaDoMes;
		this.saldoDoMes = saldoDoMes;
	}

	public BigDecimal getReceitaDoMes() {
		return receitaDoMes;
	}

	public void setReceitaDoMes(BigDecimal receitaDoMes) {
		this.receitaDoMes = receitaDoMes;
	}

	public BigDecimal getDespesaDoMes() {
		return despesaDoMes;
	}

	public BigDecimal getSaldoDoMes() {
		return saldoDoMes;
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
}
