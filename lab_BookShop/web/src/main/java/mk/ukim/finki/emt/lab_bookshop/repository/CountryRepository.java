package mk.ukim.finki.emt.lab_bookshop.repository;

import mk.ukim.finki.emt.lab_bookshop.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
