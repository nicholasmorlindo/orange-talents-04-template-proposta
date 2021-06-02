package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.AvisoViagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends CrudRepository<AvisoViagem, Long> {
}
