package tn.esprit.teriak.IServices;

import tn.esprit.teriak.Dto.OrderRequestDto;

public interface OrderService {
    void saveOrder(OrderRequestDto orderRequest);
}

