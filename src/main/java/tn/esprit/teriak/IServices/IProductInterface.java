package tn.esprit.teriak.IServices;

import tn.esprit.teriak.Entities.Product;

import java.util.List;

public interface IProductInterface {
    // method to get all products
    List<Product> getAllProducts();

    // method to get a product by ID
    Product getProductById(Long id);

    // method to get a product by codePCT
    Product getProductByCodePCT(String codePCT);

    // method to create a new product
    Product createProduct(Product product);

    // method to update an existing product
    Product updateProduct(Long id, Product product);

    // method to delete a product by ID
    boolean deleteProduct(Long id);
}
