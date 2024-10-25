package com.sala.salafacil.Repository;

import com.sala.salafacil.Entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    List<Reserva> findByUsuarioIdAndStatusTrue(Long usuarioId);
    List<Reserva> findBySalaIdAndDataReservaAndStatusTrue(Long sala_id, Date data_reserva);
    List<Reserva> findAllByUsuarioId(Long usuario_id);
    List<Reserva> findAllBySalaId(Long sala_id);
}
