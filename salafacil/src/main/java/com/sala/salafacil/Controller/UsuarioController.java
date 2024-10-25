package com.sala.salafacil.Controller;

import com.sala.salafacil.Entity.Usuario;
import com.sala.salafacil.Service.UsuarioServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServe service;

    @GetMapping
    public List<Usuario> getUsuarios() {
        return service.findAll();
    }

    @PostMapping
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = service.save(usuario);
        return usuarioSalvo;
    }
    @GetMapping("/{id}")
    public Optional<Usuario> getUsuario(@PathVariable Long id) {
        return service.findById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        service.deleteById(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = service.update(id, usuario);
            return ResponseEntity.ok(usuarioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
