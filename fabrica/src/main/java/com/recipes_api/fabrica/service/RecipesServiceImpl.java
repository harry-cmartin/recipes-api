package com.recipes_api.fabrica.service;

import com.recipes_api.fabrica.dto.RecipesDto;
import com.recipes_api.fabrica.entity.RecipesEntity;
import com.recipes_api.fabrica.exceptions.NoSuchDescription;
import com.recipes_api.fabrica.mappers.RecipesMapper;
import com.recipes_api.fabrica.repository.RecipesRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.data.web.config.EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO;

@Service
@EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO)
public class RecipesServiceImpl implements RecipeService{

    RecipesMapper recipesMapper;

    RecipesRepository recipesRepository;

    public RecipesServiceImpl(RecipesRepository recipesRepository, RecipesMapper recipesMapper) {
        this.recipesRepository = recipesRepository;
        this.recipesMapper = recipesMapper;
    }

    @Override
    @Transactional
    public RecipesDto createRecipe(RecipesDto dto) throws NoSuchElementException, NoSuchDescription{

        if(dto.getName() == null || dto.getName().isBlank()){
            throw new NoSuchElementException();
        }

        if(dto.getDescription() == null || dto.getDescription().isBlank()){
            throw new NoSuchDescription();

        }

        RecipesEntity nova = recipesMapper.toEntity(dto);

        return recipesMapper.toDto(recipesRepository.save(nova));
    }



    @Override
    @Transactional
    public ResponseEntity updateLikes(Long id) {

      try {
          RecipesEntity recipesEntity = recipesRepository.findById(id).get();

          recipesEntity.setLikes(recipesEntity.getLikes() + 1);

          recipesRepository.save(recipesEntity);

          return ResponseEntity.accepted().build();
      }
      catch (NoSuchElementException e){
          System.out.println(e.getMessage());
          return ResponseEntity.notFound().build();
      }

    }

    @Override
    @Transactional
    public void updateRecipe(RecipesDto dto, Long id) {

        RecipesEntity recipesEntity = recipesRepository.findById(id).get();

        if(dto.getName() != null ){
            recipesEntity.setName(dto.getName());

        }

        if(dto.getDescription() != null){
            recipesEntity.setDescription(dto.getDescription());
        }

        recipesEntity.setUpdatedAt(LocalDateTime.now());

        recipesRepository.save(recipesEntity);

    }

    @Override
    @Transactional
    public void deleteRecipe(Long id) {

        recipesRepository.deleteById(id);

    }

    @Override

    public Page<RecipesDto> getAllRecipes(Pageable pageable) {


        Page<RecipesEntity> recipes =  recipesRepository.findAll(pageable);


        return recipes.map(entity ->

            RecipesDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .category(entity.getCategory())
                    .likes(entity.getLikes())
                    .description(entity.getDescription())
                    .createdAt(entity.getCreatedAt()).build()

        );

    }


}
