package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private Long id;

    @NotNull(message = "Название книги не может быть пустым")
    @Size(min = 1, max = 255)
    private String title;

    @NotNull(message = "Автор не может быть пустым")
    @Size(min = 1, max = 255)
    private String author;

    @NotNull(message = "ISBN не может быть пустым")
    @Size(min = 10, max = 13)
    private String ISBN;

    @NotNull(message = "Год публикации обязателен")
    private Integer publicationYear;

    @NotNull(message = "Общее количество копий обязательно")
    private Integer totalCopies;

    @NotNull(message = "Количество доступных копий обязательно")
    private Integer availableCopies;
}
