package com.recipes_api.fabrica.mappers;

import com.recipes_api.fabrica.dto.RecipesDto;
import com.recipes_api.fabrica.entity.RecipesEntity;
import org.springframework.stereotype.Component;

@Component
public class RecipesMapper {

    public RecipesEntity toEntity(RecipesDto dto) {


        try{

            RecipesEntity entity = new RecipesEntity();

            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            entity.setCategory(dto.getCategory());
            entity.setLikes(dto.getLikes());
            entity.setCreatedAt(dto.getCreatedAt());

            return entity;


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return null;

    }

    public RecipesDto toDto(RecipesEntity entity) {

        try {

            RecipesDto dto = RecipesDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .description(entity.getDescription())
                    .category(entity.getCategory())
                    .likes(entity.getLikes())
                    .createdAt(entity.getCreatedAt())
                    .build();

            return dto;

        }catch (Exception e){

            System.out.println(e.getMessage());

        }

        return null;
    }


}
