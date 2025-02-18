package org.iesvdm.tutorial.repository;

import org.iesvdm.tutorial.domain.HeroeHasPoder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HasPoderRepository extends JpaRepository<HeroeHasPoder, Long> {
}
