package com.gft.repositories;

import com.gft.entities.Starter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StarterRepository extends JpaRepository<Starter, Long> {

  @Query(value = "select s from Starter s where s.nome like %?1%")
  List<Starter> findByNome(String nome);
}
