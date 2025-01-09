package org.uniplovdiv.fmi.springjpa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.uniplovdiv.fmi.springjpa.data.Country;
import org.uniplovdiv.fmi.springjpa.data.Region;
import org.uniplovdiv.fmi.springjpa.repo.CountriesRepository;
import org.uniplovdiv.fmi.springjpa.repo.RegionsRepository;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class SpringJpaApplication implements CommandLineRunner {

    private final RegionsRepository regionsRepository;
    private final CountriesRepository countriesRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        // Retrieve a region by id
        Region region = regionsRepository.findById(1).get();
        log.info("Region with id: {}, name: {}", region.getId(), region.getName());

        // Create a new region
/*
        region = new Region();
        region.setName("Antarctica");
        Region savedRegion = regionsRepository.save(region);
        log.info("Saved region with id: {}, name: {}", savedRegion.getId(), savedRegion.getName());
*/

        // Create a new region with countries
/*
        region = new Region();
        region.setName("Балканите");
        region.getCountries().add(new Country("MK", "Северна Македония", region));
        region.getCountries().add(new Country("AL", "Албания", region));
        Region savedRegion = regionsRepository.save(region);
        log.info("Saved region with id: {}, name: {}, countries: {}", savedRegion.getId(), savedRegion.getName(), savedRegion.getCountries());
*/

        regionsRepository.findByName("Балканите").ifPresent(regionsRepository::delete);

        Country country = countriesRepository.findById("CH").get();
        country.setName("Switzerland");
        countriesRepository.save(country);
    }
}
