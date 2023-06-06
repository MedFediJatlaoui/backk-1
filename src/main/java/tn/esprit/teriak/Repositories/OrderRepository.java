package tn.esprit.teriak.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.teriak.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

