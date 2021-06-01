package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Cartao;
import com.nicholasmorlin.proposta.model.Proposta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

    @Query(value = "select * from propostas p where p.cpf_or_cnpj = ?1", nativeQuery = true)
    public Optional<Proposta> findByCpfOrCnpj(String cpfOrCnpj);

    @Query(value = "select * from propostas p where p.cartao_id is null and p.status = 'ELEGIVEL'", nativeQuery = true)
    List<Proposta> findByCartaoIsNullAndStatusElegivel();
}
