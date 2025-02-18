package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.Poder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoderRepository  extends JpaRepository<Poder, Long> {
}
