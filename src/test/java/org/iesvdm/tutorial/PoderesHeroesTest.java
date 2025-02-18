package org.iesvdm.tutorial;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.iesvdm.tutorial.domain.Heroe;
import org.iesvdm.tutorial.domain.HeroeHasPoder;
import org.iesvdm.tutorial.domain.Mision;
import org.iesvdm.tutorial.domain.Poder;
import org.iesvdm.tutorial.repository.HasPoderRepository;
import org.iesvdm.tutorial.repository.HeroeRepository;
import org.iesvdm.tutorial.repository.MisionRepository;
import org.iesvdm.tutorial.repository.PoderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class PoderesHeroesTest {

    @Autowired
    HeroeRepository heroeRepository;

    @Autowired
    MisionRepository misionsRepository;

    @Autowired
    HasPoderRepository hasPoderRepository;

    @Autowired
    PoderRepository poderRepository;

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    private PlatformTransactionManager transactionManager;
    private TransactionTemplate transactionTemplate;

    @BeforeEach
    public void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
    }


    @Test
    @Order(1)
    void crearHeroeMisionPoderTest() {

        // Crear una misión
        Mision mision = Mision.builder()
                .descripcion("Salvar el mundo")
                .villano("Thanos")
                .build();

        // Crear un heroe
        Heroe heroe = Heroe.builder()
                .nombre("Wolverine")
                .build();
        this.heroeRepository.save(heroe);

        // Crear un poder
        Poder poder = Poder.builder()
                .nombre("Regeneración")
                .build();

        this.poderRepository.save(poder);

        // Crear la relación entre heroe y poder
        HeroeHasPoder heroeHasPoder = HeroeHasPoder.builder()
                .heroe(heroe)
                .poder(poder)
                .build();
        this.hasPoderRepository.save(heroeHasPoder);

        // Asignar el poder al heroe
        heroe.getPoderes().add(heroeHasPoder);
        // Asignar la misión al heroe
        heroe.setMision(mision);
        // Asignar el heroe a la misión
        mision.getHeroes().add(heroe);
        // Guardar la misión
        mision = this.misionsRepository.save(mision);
        // Guardar el heroe
        this.heroeRepository.save(heroe);


    }

    @Test
    @Order(2)
    void listarTodoDesdeMision() {

        transactionTemplate.executeWithoutResult(transactionStatus -> {

            misionsRepository.findById(1L).ifPresentOrElse(mision -> {
                System.out.println("Misión: " + mision.toString());
            }, () -> {
                System.out.println("La misión con ID 1 no existe.");
            });

        });

    }



    @Test
    @Order(3)
    void desasociarHeroeDeMision() {

        transactionTemplate.executeWithoutResult(transactionStatus -> {

            // Buscar el heroe
            Heroe heroe = heroeRepository.findById(3L).orElse(null);
            if (heroe != null) {
                // Desasociar el heroe de la misión
                heroe.setMision(null);
                // Guardar el heroe
                heroeRepository.save(heroe);
            }

        });

    }

    @Test
    @Order(4)
    void asociarHeroeAMision() {

        transactionTemplate.executeWithoutResult(transactionStatus -> {

            // Buscar el heroe
            Heroe heroe = heroeRepository.findById(3L).orElse(null);
            if (heroe != null) {
                //  misión
                Mision mision = Mision.builder()
                        .descripcion("Salvar el mundo2")
                        .villano("Thanos2")
                        .build();
                if (mision != null) {
                    // Asociar el heroe a la misión
                    heroe.setMision(mision);
                    // Asignar el heroe a la misión
                    mision.getHeroes().add(heroe);
                    // Guardar la misión
                    mision = this.misionsRepository.save(mision);

                    // Guardar el heroe
                    heroeRepository.save(heroe);
                }
            }

        });

    }

    @Test
    @Order(5)
    void eliminarHeroe() {

        transactionTemplate.executeWithoutResult(transactionStatus -> {

            // Buscar el heroe
            Heroe heroe = heroeRepository.findById(3L).orElse(null);
            if (heroe != null) {

                // Desasociar la misión del héroe
                if (heroe.getMision() != null) {
                    heroe.setMision(null);
                    heroeRepository.save(heroe);
                }

                // Desasociar los poderes del héroe
                for (HeroeHasPoder heroeHasPoder : heroe.getPoderes()) {
                    hasPoderRepository.delete(heroeHasPoder);
                }

                // Eliminar el héroe
                heroeRepository.delete(heroe);

            }

        });

    }



}
