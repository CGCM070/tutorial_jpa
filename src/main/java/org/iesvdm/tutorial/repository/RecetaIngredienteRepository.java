package org.iesvdm.tutorial.repository;


import org.iesvdm.tutorial.domain.RecetaHasIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaIngredienteRepository extends JpaRepository<RecetaHasIngrediente, RecetaHasIngrediente.Pk> {
}
