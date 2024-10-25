package com.sala.salafacil.Repository;

import com.sala.salafacil.Entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Long> {
}
