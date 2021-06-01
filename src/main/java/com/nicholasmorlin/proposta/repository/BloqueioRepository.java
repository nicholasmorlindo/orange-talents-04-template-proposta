package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Bloqueio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BloqueioRepository extends CrudRepository<Bloqueio, Long> {
}
