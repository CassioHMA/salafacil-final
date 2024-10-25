package com.sala.salafacil.Controller;

import com.sala.salafacil.Entity.Reserva;
import com.sala.salafacil.Repository.ReservaRepository;
import com.sala.salafacil.Service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "reserva")
public class ReservaController {

    @Autowired
    private ReservaService service;
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public List<Reserva> findAll(){
        return service.findALL();
    }
    @PostMapping
    public ResponseEntity<Reserva> createReserva(@RequestBody Reserva reserva) {
        boolean usuarioPossuiReserva = service.usuarioPossuireserva_horario(reserva.getUsuario().getId(), reserva.getDataReserva());

        if (usuarioPossuiReserva) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(reserva);
        }

        LocalDate dataAtual = LocalDate.now();
        LocalDate dataReserva = reserva.getDataReserva().toLocalDate();

        if(dataReserva.isBefore(dataAtual)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(reserva);
        }

        long diasDeAntecedencia = ChronoUnit.DAYS.between(dataAtual, dataReserva);
        if(diasDeAntecedencia > 30){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(reserva);
        }

        boolean salaJaReservada = service.salaJaReservada(reserva.getSala().getId(), reserva.getDataReserva());
        if (salaJaReservada) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(reserva);
        }

        Reserva novaReserva = service.createReserva(reserva);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReserva);
    }

    @PutMapping
    public ResponseEntity<Reserva> updateReserva(@RequestBody Reserva reservaAtualizada) {
        Reserva reserva = service.updateReserva(reservaAtualizada);
        return ResponseEntity.status(HttpStatus.OK).body(reserva);
    }

    @DeleteMapping
    public ResponseEntity<Reserva> deleteReserva(@RequestBody Reserva id_reserva) {
        service.deleteReserva(id_reserva);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> findById(@PathVariable Long id) {
        Reserva reserva = service.findById(id);
        return ResponseEntity.ok(reserva);
    }

    @GetMapping("/{usuario_id}/usuario")
    public ResponseEntity<List<Reserva>> getReservasByUsuario(@PathVariable Long usuario_id) {
        List<Reserva> reservas = service.getReservasByUsuarioId(usuario_id);
        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reservas);
    }
    @GetMapping("/{sala_id}/sala")
    public ResponseEntity<List<Reserva>> getReservasBySala(@PathVariable Long sala_id) {
        List<Reserva> reservas = service.getReservasBySalaId(sala_id);
        if (reservas.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(reservas);
    }
}
