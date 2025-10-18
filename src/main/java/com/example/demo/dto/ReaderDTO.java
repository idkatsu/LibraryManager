package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDTO {

    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    @Size(min = 1, max = 255)
    private String lastName;

    @NotNull
    @Email(message = "Неверный формат email")
    private String email;

    @NotNull
    @Pattern(regexp = "^\\+?[0-9]{10,14}$", message = "Неверный номер телефона")
    private String phone;

    @NotNull
    private LocalDate registrationDate;
}
