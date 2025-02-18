package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Mision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MisionRepository  extends JpaRepository<Mision, Long> {
}
