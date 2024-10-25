package com.sala.salafacil.Entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;

@Entity(name = "reserva")
public class Reserva implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long id;

    @Column(name = "data_reserva")
    Date dataReserva;

    @Column(name = "data_pedido")
    Date dataPedido;

    @Column(name = "status")
    Boolean status;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    Sala sala;


    public Reserva() {
    }

    public Reserva(Date dataPedido, Date dataReserva, Long id, Boolean status, Usuario usuario, Sala sala) {
        this.dataPedido = dataPedido;
        this.dataReserva = dataReserva;
        this.id = id;
        this.status = status;
        this.usuario = usuario;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }
}
