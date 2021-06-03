package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Carteira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteiraRepository extends CrudRepository<Carteira,Long> {
}
