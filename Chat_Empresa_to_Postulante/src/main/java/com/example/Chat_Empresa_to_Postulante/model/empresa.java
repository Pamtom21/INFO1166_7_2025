package com.example.Chat_Empresa_to_Postulante.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "empresa")
public class empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpresa;
    private String direccion;
    private String vacante;
    private String cargo;
    private String descripcion;
    private LocalDate fechaPostulacion;

    public empresa() {}

    // Getters y Setters
    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getVacante() { return vacante; }
    public void setVacante(String vacante) { this.vacante = vacante; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaPostulacion() { return fechaPostulacion; }
    public void setFechaPostulacion(LocalDate fechaPostulacion) { this.fechaPostulacion = fechaPostulacion; }
}
