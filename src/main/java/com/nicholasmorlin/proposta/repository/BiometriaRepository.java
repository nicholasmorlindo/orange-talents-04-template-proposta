package com.nicholasmorlin.proposta.repository;

import com.nicholasmorlin.proposta.model.Biometria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BiometriaRepository extends CrudRepository<Biometria,Long> {
}
