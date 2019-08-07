package com.algaworks.algamoney.api.repository;

import com.algaworks.algamoney.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    //TODO https://www.youtube.com/watch?v=bUBIkFnJqNc
    //todo IgnoreCase = Tudo maiusculo
    //Containing = busca flexivel
    public List<Pessoa> findByNomeIgnoreCaseContaining(String name);

}
