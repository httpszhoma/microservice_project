package zhoma.inventory_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zhoma.inventory_service.dto.InventoryResponse;
import zhoma.inventory_service.repository.InventoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;


    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> sku_code) {
        return inventoryRepository.findBySkuCodeIn(sku_code)
                .stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build())
                        .toList();

    }
//    @Transactional(readOnly = true)
//    @SneakyThrows
//    public List<InventoryResponse> isInStock(List<String> skuCode) {
//        log.info("Checking Inventory");
//        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
//                .map(inventory ->
//                        InventoryResponse.builder()
//                                .skuCode(inventory.getSkuCode())
//                                .isInStock(inventory.getQuantity() > 0)
//                                .build()
//                ).toList();
//    }
}
