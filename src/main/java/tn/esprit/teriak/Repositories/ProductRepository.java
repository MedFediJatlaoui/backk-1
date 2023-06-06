package tn.esprit.teriak.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.teriak.Entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCodePCT(String codePCT);
}
