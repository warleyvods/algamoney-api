package com.algaworks.algamoney.api.service;

import com.algaworks.algamoney.api.model.Lancamento;
import com.algaworks.algamoney.api.model.Pessoa;
import com.algaworks.algamoney.api.repository.LancamentoRespository;
import com.algaworks.algamoney.api.repository.PessoaRepository;
import com.algaworks.algamoney.api.service.exception.PessoaInexisteOuInativaException;
import org.springframework.stereotype.Service;

@Service
public class LancamentoService {

    private final PessoaRepository pessoaRepository;

    private final LancamentoRespository lancamentoRespository;

    public LancamentoService(PessoaRepository pessoaRepository, LancamentoRespository lancamentoRespository) {
        this.pessoaRepository = pessoaRepository;
        this.lancamentoRespository = lancamentoRespository;
    }


    public Lancamento salvar(Lancamento lancamento) {
        Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
        if (pessoa == null || pessoa.isInativo()) {
            throw new PessoaInexisteOuInativaException();
        }
        return lancamentoRespository.save(lancamento);
    }

}
