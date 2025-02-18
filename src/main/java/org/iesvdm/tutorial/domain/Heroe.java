package org.iesvdm.tutorial.domain;

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
        property = "id", scope = Heroe.class)
public class Heroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private long id;

    @Column(length = 45)
    private String nombre;

    private LocalDateTime fechaNac;

    @ManyToOne
    @ToString.Exclude
    private Mision mision;

    @OneToMany(mappedBy = "heroe")
    @Builder.Default
    private Set<HeroeHasPoder> poderes = new HashSet<>();

}
