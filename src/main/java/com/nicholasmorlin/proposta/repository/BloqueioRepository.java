package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Bloqueio;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueioRepository extends CrudRepository<Bloqueio, Long> {

    @Query(value = "select * from bloqueios b where b.cartao_id = ?1", nativeQuery = true)
    Optional<Bloqueio> findByCartaoBloqueio(Long id);
}
