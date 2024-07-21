package zhoma.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zhoma.inventory_service.model.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {

    Optional<Inventory> findBySkuCode(String skuCode);

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
