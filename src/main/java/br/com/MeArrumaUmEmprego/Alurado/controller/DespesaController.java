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

import br.com.MeArrumaUmEmprego.Alurado.Dto.DespesaDtoCompleto;
import br.com.MeArrumaUmEmprego.Alurado.Repository.DespesaRepository;
import br.com.MeArrumaUmEmprego.Alurado.formulario.DespesaAtualizar;
import br.com.MeArrumaUmEmprego.Alurado.formulario.DespesaFormulario;
import br.com.MeArrumaUmEmprego.Alurado.model.Despesa;

@RestController
@RequestMapping("despesas")
public class DespesaController {

	private DespesaRepository despesaRepository;

	public DespesaController(DespesaRepository despesaRepository) {
		this.despesaRepository = despesaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<DespesaDtoCompleto> cadastrarNovaDespesa(@Valid @RequestBody DespesaFormulario formulario,
			UriComponentsBuilder uriBuilder) {
		
		Despesa despesa = formulario.novaDespesaComCategoria();

		if (despesaRepository.existsByDescricao(despesa.getDescricao())) {
			return ResponseEntity.badRequest().build();
		}
		despesaRepository.save(despesa);
		URI uri = uriBuilder.path("/despesas/{id}").buildAndExpand(despesa.getId()).toUri();

		return ResponseEntity.created(uri).body(new DespesaDtoCompleto(despesa));
	}
	
	@GetMapping
	public ResponseEntity<List<DespesaDtoCompleto>>minhasDespesas(@RequestParam (value="descricao", required = false) String descricao){
		Optional<Despesa>optional = despesaRepository.findByDescricao(descricao);
		if(optional.isPresent()) {
			List<Despesa>despesas= despesaRepository.listDescricao(descricao);
				return ResponseEntity.ok(DespesaDtoCompleto.converterLista(despesas));
		}		
		List<Despesa>despesas = despesaRepository.findAll();		
		return ResponseEntity.ok(DespesaDtoCompleto.converterLista(despesas));	
	}
	
	@GetMapping(path="/{id}")
	public ResponseEntity<DespesaDtoCompleto>despesaDetalhada(@PathVariable Long id){		
		Optional<Despesa>optional = despesaRepository.findById(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(new DespesaDtoCompleto(optional.get()));	
		}
			return ResponseEntity.badRequest().build();	
	}
	
	@DeleteMapping(path="/{id}")
	@Transactional
	public ResponseEntity<DespesaDtoCompleto>apagarDespesa(@PathVariable Long id){
		Optional<Despesa> optional = despesaRepository.findById(id);
		if(optional.isPresent()) {
			despesaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	@PutMapping(path="/{id}")
	@Transactional
	public ResponseEntity<DespesaDtoCompleto>atualizarDespesa(@PathVariable Long id, @Valid @RequestBody DespesaAtualizar atualizar){
		Optional <Despesa>optional = despesaRepository.findById(id);
		if(optional.isPresent()) {
			if(despesaRepository.existsByDescricao(atualizar.getDescricao())) {
				return ResponseEntity.badRequest().build();
			}
		Despesa despesa = atualizar.despesaAtualizar(id, despesaRepository);
		return ResponseEntity.ok(new DespesaDtoCompleto(despesa));
		}
		return  ResponseEntity.badRequest().build();
	}	
	@GetMapping("/{ano}/{mes}")
	public ResponseEntity<List<DespesaDtoCompleto>>buscarPorAnoEMes(@PathVariable("ano") String ano, @PathVariable ("mes") String mes){	
		Optional<List<Despesa>>optional = despesaRepository.ChecarSerExisteMesEANo(ano, mes);
		if(optional.isPresent()) {
			List<Despesa>receitas = despesaRepository.listaDespesaAnoEMes(ano, mes);
			return ResponseEntity.ok(DespesaDtoCompleto.converterLista(receitas));		
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	
}
