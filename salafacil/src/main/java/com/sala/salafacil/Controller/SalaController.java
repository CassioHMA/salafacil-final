package com.sala.salafacil.Controller;

import com.sala.salafacil.Entity.Reserva;
import com.sala.salafacil.Entity.Sala;

import com.sala.salafacil.Service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/salas")
public class SalaController {

    @Autowired
    private SalaService service;

    @PostMapping
    public ResponseEntity<Sala> create(@RequestBody Sala salaNova) {
        Sala sal = service.create(salaNova);
        return ResponseEntity.ok(sal);
    }

    @GetMapping
    public ResponseEntity<List<Sala>> findAll() {
        List<Sala> salas = service.findAll();
        if (salas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(salas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sala> findById(@PathVariable Long id) {
        Sala sala = service.findById(id);
        if (sala != null) {
            return ResponseEntity.ok(sala);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sala> updateSala(@PathVariable Long id, @RequestBody Sala sala) {
        try {
            Sala salaAtualizada = service.update(id, sala);
            return ResponseEntity.ok(salaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSala(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
