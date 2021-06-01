package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Cartao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaoRepository extends CrudRepository<Cartao, Long> {
}
