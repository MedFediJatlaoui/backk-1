package tn.esprit.teriak.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.teriak.Entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
