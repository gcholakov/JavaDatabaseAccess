package org.uniplovdiv.fmi.springjpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.uniplovdiv.fmi.springjpa.data.Country;

public interface CountriesRepository extends JpaRepository<Country, String> {

}