package br.com.MeArrumaUmEmprego.Alurado.controller;

import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.MeArrumaUmEmprego.Alurado.Repository.ResumoRepository;
import br.com.MeArrumaUmEmprego.Alurado.Util.Atualizador;

@RestController
@RequestMapping("resumo")
public class ResumoController {

	private ResumoRepository resumoRepository;

	public ResumoController(ResumoRepository resumoRepository) {
		this.resumoRepository = resumoRepository;
	}

	@GetMapping("{ano}/{mes}")
	@Transactional
	public ResponseEntity<Object> resumo(@PathVariable String mes, @PathVariable String ano) {
		Object resumo = resumoRepository.resumoDoMes(mes, ano);
		if (resumoRepository.check(mes, ano) == 1) {
			Atualizador att = new Atualizador(resumoRepository);
			att.updateOutros(mes, ano);
			att.updateCategoria();

			return ResponseEntity.ok(resumo);
		}
		if (resumoRepository.checkDespesa(mes, ano) != 1 && resumoRepository.checkReceita(mes, ano) != 1) {
			Atualizador att = new Atualizador(resumoRepository);
			att.updateOutros(mes, ano);
			att.updateCategoria();

			return ResponseEntity.ok(resumo);
		}
		if (resumoRepository.checkDespesa(mes, ano) == 1) {
			Atualizador att = new Atualizador(resumoRepository);
			att.updateOutros(mes, ano);
			att.updateCategoria();
			return ResponseEntity.ok(resumo);
		}
		Atualizador att = new Atualizador(resumoRepository);
		att.updateOutros(mes, ano);
		att.updateCategoria();
		return ResponseEntity.ok(resumo);

	}
}
