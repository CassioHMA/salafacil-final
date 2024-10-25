package com.sala.salafacil.Service;

import com.sala.salafacil.Entity.Reserva;
import com.sala.salafacil.Repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;
    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findALL(){
        return repository.findAll();
    }

    public boolean usuarioPossuireserva_horario(Long usuario_id, Date data_reserva){
       List<Reserva> reservas = repository.findByUsuarioIdAndStatusTrue(usuario_id);


       for (Reserva reservaExistente : reservas) {
           if (reservaExistente.getDataReserva().equals(data_reserva)) {
               return true;
           }
       }
       return false;
    }

    public boolean salaJaReservada(Long sala_id, Date data_reserva){
        List<Reserva> reservas = reservaRepository.findBySalaIdAndDataReservaAndStatusTrue(sala_id, data_reserva);
        return !reservas.isEmpty();

    }

    public Reserva createReserva(Reserva reserva){
        return repository.save(reserva);
    }

    public Reserva updateReserva(Reserva reservaAtualizada) {
        if (reservaAtualizada.getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID da reserva não fornecido");
        }

        Reserva reservaExistente = repository.findById(reservaAtualizada.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva com ID " + reservaAtualizada.getId() + " não encontrada")
        );

        reservaExistente.setDataPedido(reservaAtualizada.getDataPedido());
        reservaExistente.setDataReserva(reservaAtualizada.getDataReserva());
        reservaExistente.setStatus(reservaAtualizada.getStatus());
        reservaExistente.setUsuario(reservaAtualizada.getUsuario());

        return repository.save(reservaExistente);
    }

    public void deleteReserva(Reserva id_reserva){
        Optional<Reserva> reserva = repository.findById(id_reserva.getId());
        if(reserva.isPresent()){
            repository.delete(reserva.get());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Rserva com ID" + id_reserva + "não encontrada");
        }
    }

    public Reserva findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva com ID " + id + " não encontrada")
        );
    }

    public List<Reserva> getReservasByUsuarioId(Long usuarioId) {
        return reservaRepository.findAllByUsuarioId(usuarioId);
    }

    public List<Reserva> getReservasBySalaId(Long sala_id) {
        return reservaRepository.findAllBySalaId(sala_id);
    }


}
