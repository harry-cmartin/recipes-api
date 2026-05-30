package com.recipes_api.fabrica.controller;


import com.recipes_api.fabrica.dto.RecipesDto;
import com.recipes_api.fabrica.entity.RecipesEntity;
import com.recipes_api.fabrica.service.RecipesServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/recipes")
public class RecipesController {

    @Autowired
    RecipesServiceImpl recipeService;



    @GetMapping
    public ResponseEntity<Page<RecipesDto>> getAllRecipes(
            @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<RecipesDto> recipes = recipeService.getAllRecipes(pageable);
        return ResponseEntity.ok(recipes);
    }


    @PostMapping
    public ResponseEntity<RecipesDto> createRecipe(@RequestBody RecipesDto dto) {

        RecipesDto novo = recipeService.createRecipe(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novo.getId())
                .toUri();

        return ResponseEntity.created(location).body(novo);

    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> updateLikes(@PathVariable Long id) {


        ResponseEntity statusLikes = recipeService.updateLikes(id);

        return ResponseEntity.status(statusLikes.getStatusCode()).build();

    }

    @PatchMapping("/{id}")
    public void updateRecipe(@RequestBody RecipesDto dto, @PathVariable Long id) {

        recipeService.updateRecipe(dto, id);
    }

    @DeleteMapping("/remove/{id}")
    public void deleteRecipe(@PathVariable Long id) {

        recipeService.deleteRecipe(id);
    }




}
