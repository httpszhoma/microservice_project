    package zhoma.inventory_service.controller;


    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpStatus;
    import org.springframework.web.bind.annotation.*;
    import zhoma.inventory_service.dto.InventoryResponse;
    import zhoma.inventory_service.service.InventoryService;

    import java.util.List;

    @RestController
    @RequestMapping("/api/inventory")
    @RequiredArgsConstructor
    public class InventoryController {

        private final InventoryService service;

        @GetMapping()
        @ResponseStatus(HttpStatus.OK)
        public List<InventoryResponse> inInStock(@RequestParam List<String> skuCode) {
            return service.isInStock(skuCode);

        }


    }
