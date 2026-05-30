package com.recipes_api.fabrica.dto;


import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@Value
@Builder
@Jacksonized
public class RecipesDto {

    private Long id;

    private String category;

    private String name;

    private String description;

    private Integer likes;

    private LocalDateTime createdAt;

}
