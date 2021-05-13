package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Proposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    @Query(value = "select * from propostas p where p.cpf_or_cnpj = ?1", nativeQuery = true)
    public Optional<Proposta> findByCpfOrCnpj(String cpfOrCnpj);
}
