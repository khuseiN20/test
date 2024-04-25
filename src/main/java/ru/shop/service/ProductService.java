package ru.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.shop.exception.EntityNotFoundException;
import ru.shop.model.Product;
import ru.shop.model.ProductType;
import ru.shop.repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final IRepository<Product> repository;


    public void save(Product product) {
        repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product getById(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);

//        return repository.findAll().stream().
//                filter(product -> product.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new EntityNotFoundException());
    }

    public List<Product> findByProductType(ProductType productType) {
        List<Product> result = new ArrayList<>();
        for (Product product : repository.findAll()) {
            if (product.getProductType() == productType) {
                result.add(product);
            }
        }
        return result;
    }
}
