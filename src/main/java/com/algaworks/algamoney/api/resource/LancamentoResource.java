package com.algaworks.algamoney.api.resource;

import com.algaworks.algamoney.api.event.RecursoCriadoEvent;
import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.repository.LancamentosRespository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController("/lancamentos")
public class LancamentoResource {

    private final ApplicationEventPublisher publisher;
    private final LancamentosRespository lancamentosRespository;

    public LancamentoResource(ApplicationEventPublisher publisher, LancamentosRespository lancamentosRespository) {
        this.publisher = publisher;
        this.lancamentosRespository = lancamentosRespository;
    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
        Lancamento lancamentoSalvo = lancamentosRespository.save(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
    }

    @GetMapping
    public ResponseEntity<?> listarTodos() {
        return new ResponseEntity<>(lancamentosRespository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarPeloCodigo (@PathVariable Long codigo) {
        Lancamento lancamento = lancamentosRespository.findOne(codigo);
        return lancamento != null ?  new ResponseEntity<>(lancamento, HttpStatus.OK) : new ResponseEntity<>(lancamento, HttpStatus.NOT_FOUND);
    }



}
