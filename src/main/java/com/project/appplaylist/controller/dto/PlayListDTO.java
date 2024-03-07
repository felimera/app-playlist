package com.project.appplaylist.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;

public class PlayListDTO {
    @NotEmpty(message = "The play list  name cannot be empty.")
    @NotNull(message = "The play list  name cannot be null.")
    @Size(max = 100, message = "The play list  name only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$",message = "It is not a valid name.")
    private String nombre;
    @Size(max = 1000, message = "The play list  name only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$",message = "It is not a valid name.")
    private String descripcion;
    private List<CancionDTO> canciones;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<CancionDTO> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<CancionDTO> canciones) {
        this.canciones = canciones;
    }
}
