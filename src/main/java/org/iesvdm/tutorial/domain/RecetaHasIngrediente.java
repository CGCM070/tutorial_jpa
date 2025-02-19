package org.iesvdm.tutorial.domain;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@Entity
public class RecetaHasIngrediente {

    @Embeddable
    public static class Pk implements Serializable {

        private Long recetaId;
        private Long ingredienteId;
    }

    @EmbeddedId
    private Pk pk;


    @ManyToOne
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    @EqualsAndHashCode.Include
    private Receta receta;

    public void setReceta(Receta receta) {
        this.receta = receta;
        if (receta != null) {
            receta.getRecetaHasIngredientes().add(this);
        }
    }


    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    @EqualsAndHashCode.Include
    private Ingrediente ingrediente;


    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
        if (ingrediente != null) {
            ingrediente.getRecetaHasIngredientes().add(this);
        }
    }
}
