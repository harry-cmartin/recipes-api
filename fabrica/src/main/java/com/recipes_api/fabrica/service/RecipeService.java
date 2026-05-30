package com.recipes_api.fabrica.service;

import com.recipes_api.fabrica.dto.RecipesDto;
import com.recipes_api.fabrica.entity.RecipesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecipeService {

    RecipesDto createRecipe(RecipesDto dto);


    ResponseEntity updateLikes(Long id);

    void updateRecipe(RecipesDto dto , Long id);

    void deleteRecipe(Long id);

    Page<RecipesDto> getAllRecipes(Pageable pageable);



}
