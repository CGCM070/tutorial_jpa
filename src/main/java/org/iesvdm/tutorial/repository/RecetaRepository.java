package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecetaRepository  extends JpaRepository<Receta, Long> {
}
