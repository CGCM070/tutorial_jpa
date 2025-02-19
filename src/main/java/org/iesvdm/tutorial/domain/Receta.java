package org.iesvdm.tutorial.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id", scope = Receta.class)


public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private  Long id;

    @Column(length = 45)
    private String nombre;
    private String descripcion;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private LocalDateTime creada;


    @ManyToOne
    private Chef chef;

    @ManyToOne
    private CategoriaReceta categoriaReceta;


    @OneToMany (mappedBy = "receta")
    @Builder.Default
    private Set<RecetaHasIngrediente> recetaHasIngredientes = new HashSet<>();

}
