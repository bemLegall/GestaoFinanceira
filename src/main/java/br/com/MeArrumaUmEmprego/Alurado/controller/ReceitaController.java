package br.com.MeArrumaUmEmprego.Alurado.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.MeArrumaUmEmprego.Alurado.Dto.ReceitaDtoCompleto;
import br.com.MeArrumaUmEmprego.Alurado.Repository.ReceitaRepository;
import br.com.MeArrumaUmEmprego.Alurado.formulario.ReceitaAtualizar;
import br.com.MeArrumaUmEmprego.Alurado.formulario.ReceitaFormulario;
import br.com.MeArrumaUmEmprego.Alurado.model.Receita;

@RestController
@RequestMapping("receitas")
public class ReceitaController {

	private ReceitaRepository receitaRepository;

	public ReceitaController(ReceitaRepository receitaRepository) {
		this.receitaRepository = receitaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<ReceitaDtoCompleto> novaReceita(@Valid @RequestBody ReceitaFormulario formulario,
			UriComponentsBuilder uriBuilder) {
		Receita receita = formulario.novaReceita();

		if (receitaRepository.existsByDescricao(receita.getDescricao())) {

			return ResponseEntity.badRequest().build();
		}
		receitaRepository.save(receita);
		URI uri = uriBuilder.path("/receitas/{id}").buildAndExpand(receita.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReceitaDtoCompleto(receita));
	}

	@GetMapping
	public ResponseEntity<List<ReceitaDtoCompleto>> minhaReceita(
			@RequestParam(value = "descricao", required = false) String descricao) {
		Optional<Receita> optional = receitaRepository.findByDescricao(descricao);
		if (optional.isPresent()) {
			List<Receita> receitas = receitaRepository.listDescricao(descricao);
			return ResponseEntity.ok(ReceitaDtoCompleto.converteToList(receitas));
		}
		List<Receita> receita = receitaRepository.findAll();
		return ResponseEntity.ok(ReceitaDtoCompleto.converteToList(receita));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<ReceitaDtoCompleto> detalhesDaReceita(@PathVariable Long id) {
		Optional<Receita> optional = receitaRepository.findById(id);
		if (optional.isPresent()) {
			return ResponseEntity.ok(new ReceitaDtoCompleto(optional.get()));
		}
		return ResponseEntity.badRequest().build();
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<ReceitaDtoCompleto> atualizar(@PathVariable Long id,
			@RequestBody @Valid ReceitaAtualizar atualizar) {
		Optional<Receita> optional = receitaRepository.findById(id);
		if (optional.isPresent()) {
			if (receitaRepository.existsByDescricao(atualizar.getDescricao())) {
				return ResponseEntity.badRequest().build();
			}
			Receita receita = atualizar.atualizar(id, receitaRepository);
			return ResponseEntity.ok(new ReceitaDtoCompleto(receita));
		}
		return ResponseEntity.badRequest().build();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<ReceitaDtoCompleto> deletar(@PathVariable Long id) {

		Optional<Receita> optional = receitaRepository.findById(id);
		if (optional.isPresent()) {
			receitaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}

	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<ReceitaDtoCompleto>> porMesEAno(@PathVariable String ano, @PathVariable String mes) {
		Optional<List<Receita>> optional = receitaRepository.mesEAno(ano, mes);
		if (optional.isPresent()) {
			List<Receita> receitas = receitaRepository.receitaPorMes(ano, mes);
			return ResponseEntity.ok(ReceitaDtoCompleto.converteToList(receitas));
		}
		return ResponseEntity.badRequest().build();

	}

}
