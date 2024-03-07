package com.project.appplaylist.controller.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotEmpty(message = "The customer name cannot be empty.")
    @NotNull(message = "The customer name cannot be null.")
    @Size(max = 100, message = "The customer's name only has a maximum of 100 characters.")
    @Pattern(regexp = "^[ a-zA-ZñÑáéíóúÁÉÍÓÚ]+$", message = "It is not a valid name.")
    private String name;
    @NotEmpty(message = "The customer's email cannot be empty.")
    @Email(message = "The email is not formatted correctly.")
    @Size(max = 100, message = "The customer's email only has a maximum of 100 characters.")
    private String email;
    private String password;
}
