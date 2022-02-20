package br.com.MeArrumaUmEmprego.Alurado.Util;

import br.com.MeArrumaUmEmprego.Alurado.Repository.ResumoRepository;

public class Atualizador {

	private ResumoRepository resumoRepository;

	public Atualizador(ResumoRepository resumoRepository) {
		this.resumoRepository = resumoRepository;
	}
	public void updateCategoria() {
		resumoRepository.updateCategoriaAlimentacao();
		resumoRepository.updateCategoriaEducacao();
		resumoRepository.updateCategoriaLazer();
		resumoRepository.updateCategoriaImprevisto();
		resumoRepository.updateCategoriaMoradia();
		resumoRepository.updateCategoriaOutros();
		resumoRepository.updateCategoriaTransporte();
	}
	public void updateOutros(String mes, String ano) {
		resumoRepository.updateDespesa(mes, ano);
		resumoRepository.updateReceita(mes, ano);
		resumoRepository.updateSaldo(mes, ano);
	}

}
