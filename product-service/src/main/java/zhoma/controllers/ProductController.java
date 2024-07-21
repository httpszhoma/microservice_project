package zhoma.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zhoma.dto.ProductRequest;
import zhoma.dto.ProductResponse;
import zhoma.models.Product;
import zhoma.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
     service.createProduct(request);
    }
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return service.getAllProducts();
    }

}
