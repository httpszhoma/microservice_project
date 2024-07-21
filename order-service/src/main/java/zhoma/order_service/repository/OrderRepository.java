package zhoma.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhoma.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
