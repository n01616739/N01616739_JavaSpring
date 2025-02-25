package com.example.n01616739_n01616739_test3ims.Service;

import com.example.n01616739_n01616739_test3ims.Entity.Product;
import com.example.n01616739_n01616739_test3ims.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository ProductRepository;


    public List<Product> getAllProducts() {
        return ProductRepository.findAll();
    }

    public Product addProduct(Product product) {
        return ProductRepository.save(product);
    }



    public Product updateProduct(Long id, Product updatedProduct)
    {
        if(ProductRepository.existsById(id))
        {
            updatedProduct.setId(id);
            return ProductRepository.save(updatedProduct);
        }
        return null;
    }

    public Product updateStock(Long id, int newstock)
    {
        Optional<Product> productList = ProductRepository.findById(id);
        if(productList.isPresent())
        {
            Product product = productList.get();
            product.setStock(newstock);
            return ProductRepository.save(product);
        }

        return null;
    }


    public void deleteProduct(Long id)
    {
        ProductRepository.deleteById(id);
    }


    }






