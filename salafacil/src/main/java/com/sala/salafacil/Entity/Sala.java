package com.sala.salafacil.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity(name = "sala")
public class Sala implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sala")
    private Long id;

    @Column(name = "nome")
    public String nome;

    @Column(name = "departamento")
    public String departamento;

    @Column(name = "descricao")
    public String descricao;

    @Column(name = "status")
    boolean status;

    @OneToMany(mappedBy = "sala", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reserva> reserva;

    public Sala() {
    }

    public Sala(String departamento, String descricao, Long id, String nome, boolean status) {
        this.departamento = departamento;
        this.descricao = descricao;
        this.id = id;
        this.nome = nome;
        this.status = status;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
}
