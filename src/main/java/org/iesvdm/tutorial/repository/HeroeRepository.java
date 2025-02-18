package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Heroe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroeRepository  extends JpaRepository<Heroe, Long> {
}
