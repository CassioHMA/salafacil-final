package com.sala.salafacil.Service;

import com.sala.salafacil.Entity.Reserva;
import com.sala.salafacil.Entity.Sala;
import com.sala.salafacil.Repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    @Autowired
    private SalaRepository repository;

    @GetMapping
    public List<Sala> findAll(){
        return repository.findAll();
    }

    public Sala create(Sala sala){
        return repository.save(sala);
    }

    public Sala findById(Long id) {
        Optional<Sala> sala = repository.findById(id);
        return sala.orElse(null);
    }

    public Sala update(Long id, Sala novaSala) {
        return repository.findById(id).map(salaExistente -> {
            salaExistente.setNome(novaSala.getNome());
            salaExistente.setDepartamento(novaSala.getDepartamento());
            salaExistente.setDescricao(novaSala.getDescricao());


            return repository.save(salaExistente);
        }).orElseThrow(() -> new RuntimeException("Sala não encontrada!"));
    }
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
