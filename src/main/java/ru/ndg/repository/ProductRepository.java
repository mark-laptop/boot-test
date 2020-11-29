package ru.ndg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ndg.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByPriceGreaterThanEqual(Pageable pageable, Integer min);
    Page<Product> findAllByPriceLessThanEqual(Pageable pageable, Integer max);
    Page<Product> findAllByPriceGreaterThanEqualAndPriceLessThanEqual(Pageable pageable, Integer min, Integer max);
}
