package com.algaworks.algamoney.api.resource;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.PessoaService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	private final PessoaRepository pessoaRepository;
	private final PessoaService pessoaService;
	private final ApplicationEventPublisher publisher;

	public PessoaResource(PessoaRepository pessoaRepository, PessoaService pessoaService, ApplicationEventPublisher publisher) {
		this.pessoaRepository = pessoaRepository;
		this.pessoaService = pessoaService;
		this.publisher = publisher;
	}

	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSalva = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}

	@GetMapping
	public ResponseEntity<> listar() {
		return new ResponseEntity<>(pessoaRepository.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa pessoa = pessoaRepository.findOne(codigo);
		return pessoa != null ? new ResponseEntity<>(pessoa, HttpStatus.OK) : new ResponseEntity<>(pessoa, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		pessoaRepository.delete(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
		return new ResponseEntity<>(pessoaSalva, HttpStatus.OK);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}

	@GetMapping("/buscar-por-nome/{nome}")
	public ResponseEntity<> buscarPeloNome(@PathVariable String nome) {
		pessoaRepository.findByNomeIgnoreCaseContaining(nome);
		return new ResponseEntity<>(pessoaRepository.findByNomeIgnoreCaseContaining(nome), HttpStatus.OK);
	}

}
