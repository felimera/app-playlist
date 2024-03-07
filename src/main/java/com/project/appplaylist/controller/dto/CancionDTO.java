package com.project.appplaylist.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancionDTO {
    @NotEmpty(message = "The title  cannot be empty.")
    @NotNull(message = "The title cannot be null.")
    @Size(max = 100, message = "The title's only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$",message = "It is not a valid title.")
    private String titulo;
    @NotEmpty(message = "The artist  cannot be empty.")
    @NotNull(message = "The artist cannot be null.")
    @Size(max = 100, message = "The artist's only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$",message = "It is not a valid artist.")
    private String artista;
    @NotEmpty(message = "The album  cannot be empty.")
    @NotNull(message = "The album cannot be null.")
    @Size(max = 100, message = "The album only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$",message = "It is not a valid album.")
    private String album;
    @NotEmpty(message = "The year  cannot be empty.")
    @NotNull(message = "The year cannot be null.")
    @Size(max = 10, message = "The year only has a maximum of 100 characters.")
    @Pattern(regexp = "^[A-Za-z. ]+$",message = "It is not a valid year.")
    private String anno;
    @NotEmpty(message = "The gender  cannot be empty.")
    @NotNull(message = "The gender cannot be null.")
    @Size(max = 100, message = "The gender only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$",message = "It is not a valid gender.")
    private String genero;
}
