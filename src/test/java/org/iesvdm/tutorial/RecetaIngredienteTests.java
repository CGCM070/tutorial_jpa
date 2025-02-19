package org.iesvdm.tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Ingrediente;
import org.iesvdm.tutorial.domain.Receta;
import org.iesvdm.tutorial.domain.RecetaHasIngrediente;
import org.iesvdm.tutorial.repository.IngredienteRepository;
import org.iesvdm.tutorial.repository.RecetaIngredienteRepository;
import org.iesvdm.tutorial.repository.RecetaRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class RecetaIngredienteTests {


    @Autowired
    RecetaRepository recetaRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    @Autowired
    RecetaIngredienteRepository recetaIngredienteRepository;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;


    @BeforeEach
    public void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }


    /**
     * Test relacion many-to-many con claves identificativas - clave multiple
     */

    @Test
    @Order(1)
    void grabarRecetaIngrediente() {


        //crear receta
        Receta recetaPaella = Receta.builder()
                .nombre("paella")
                .descripcion("paella valenciana")
                .creada(LocalDateTime.now())
                //tenemos Builder.Default para la coleccion de la misma
                .build();
        recetaRepository.save(recetaPaella);

        Receta recetaTortilla = Receta.builder()
                .nombre("Tortilla")
                .descripcion("Tortilla de patatas")
                .creada(LocalDateTime.now())
                //tenemos Builder.Default para la coleccion de la misma
                .build();
        recetaRepository.save(recetaTortilla);

        // ingredientes
        Ingrediente ingredienteArroz = Ingrediente.builder()
                .item("Arroz")
                .build();
        ingredienteRepository.save(ingredienteArroz);

        Ingrediente ingredienteHuevo = Ingrediente.builder()
                .item("Huevo")
                .build();
        ingredienteRepository.save(ingredienteHuevo);

        //relacion many-to-many usando la entidad intermedia


        RecetaHasIngrediente recetaIngrediente1 = RecetaHasIngrediente.builder()
                .pk(new RecetaHasIngrediente.Pk())
//                .receta(recetaPaella)
//                .ingrediente(ingredienteArroz)
                .build();

        recetaIngrediente1.setReceta(recetaPaella);
        recetaIngrediente1.setIngrediente(ingredienteArroz);


        RecetaHasIngrediente recetaIngrediente2 = RecetaHasIngrediente.builder()
                .pk(new RecetaHasIngrediente.Pk())
//                .receta(recetaTortilla)
//                .ingrediente(ingredienteHuevo)
                .build();
        recetaIngrediente2.setReceta(recetaTortilla);
        recetaIngrediente2.setIngrediente(ingredienteHuevo);

        recetaIngredienteRepository.save(recetaIngrediente2);
    }

}
