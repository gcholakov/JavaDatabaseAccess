package org.uniplovdiv.fmi.springjpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uniplovdiv.fmi.springjpa.data.Region;

import java.util.Optional;

public interface RegionsRepository extends JpaRepository<Region, Integer> {

    Optional<Region> findByName(String name);
}