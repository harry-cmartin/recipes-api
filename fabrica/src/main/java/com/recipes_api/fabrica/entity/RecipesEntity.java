package com.recipes_api.fabrica.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "recipes")
@Entity
@DynamicUpdate
public class RecipesEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipes_seq")
    @SequenceGenerator(name = "recipes_seq", sequenceName = "recipes_sequence", allocationSize = 1)
    private Long id;

    private String category;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer likes;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        setCreatedAt(LocalDateTime.now());
        setLikes(0);
        setUpdatedAt(null);
    }


}
