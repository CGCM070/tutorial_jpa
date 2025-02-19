package org.iesvdm.tutorial.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
        property = "id", scope = Ingrediente.class)

public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 45)
    private String item;

    @Column(precision = 7, scale = 2)
    private BigDecimal cantidad;

    @OneToMany(mappedBy = "ingrediente")
    @Builder.Default
    private Set<RecetaHasIngrediente> recetaHasIngredientes = new HashSet<>();

}
