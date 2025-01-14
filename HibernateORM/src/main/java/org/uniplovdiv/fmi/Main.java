package org.uniplovdiv.fmi;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.uniplovdiv.fmi.entity.Country;
import org.uniplovdiv.fmi.entity.Region;

import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HibernateORM");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        Region region = new Region();
        region.setName("Северна Африка");
        region.getCountries().add(new Country("TN", "Тунис", region));
        region.getCountries().add(new Country("MA", "Мароко", region));

        entityTransaction.begin();
        entityManager.persist(region);
        entityTransaction.commit();
        log.info("Region inserted: {}", region);

        Query query = entityManager.createQuery("select r from Region r where r.id > :regId", Region.class)
                .setParameter("regId", 10);
        List resultSet = query.getResultList();
        resultSet.forEach(r -> log.info(r.toString()));

        entityManager.close();
        entityManagerFactory.close();
    }
}