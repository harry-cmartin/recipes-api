package com.recipes_api.fabrica.repository;

import com.recipes_api.fabrica.dto.RecipesDto;
import com.recipes_api.fabrica.entity.RecipesEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RecipesRepository extends JpaRepository <RecipesEntity, Long> {

    @Query("SELECT r.name , r.description , r.likes , r.createdAt  FROM RecipesEntity r WHERE r.name = :name")
    RecipesDto getRecipeByName(@Param("name") String name);

}
