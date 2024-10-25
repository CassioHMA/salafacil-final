package com.sala.salafacil.Service;

import com.sala.salafacil.Entity.Usuario;
import com.sala.salafacil.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServe {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario save(Usuario usuario){
        Usuario usuarioSalvo = repository.save(usuario);
        return usuarioSalvo;
    }

    public Optional<Usuario> findById(Long id){
        return repository.findById(id);
    }

    public void deleteById(Long id){
        repository.deleteById(id);
    }
    public Usuario update(Long id, Usuario usuario) {
        return repository.findById(id).map(usuarioExistente -> {
            usuarioExistente.setNome(usuario.getNome());
            usuarioExistente.setEmail(usuario.getEmail());
            usuarioExistente.setPhone(usuario.getPhone());
            usuarioExistente.setCpf(usuario.getCpf());

            return repository.save(usuarioExistente);
        }).orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

}
