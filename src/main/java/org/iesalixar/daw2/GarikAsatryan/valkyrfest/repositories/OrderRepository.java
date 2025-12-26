package org.iesalixar.daw2.GarikAsatryan.valkyrfest.repositories;

import org.iesalixar.daw2.GarikAsatryan.valkyrfest.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
