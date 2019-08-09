package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRepository;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.exception.PessoaInexisteOuInativaException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private final PessoaRepository pessoaRepository;

    private final LancamentoRepository lancamentoRepository;

    public LancamentoService(PessoaRepository pessoaRepository, LancamentoRepository lancamentoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.lancamentoRepository = lancamentoRepository;
    }


    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexisteOuInativaException();
        }
        return lancamentoRepository.save(lancamento);
    }

}
