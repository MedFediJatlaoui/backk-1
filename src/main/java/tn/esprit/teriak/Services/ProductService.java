package tn.esprit.teriak.Services;

import org.springframework.stereotype.Service;
import tn.esprit.teriak.Entities.Product;
import tn.esprit.teriak.IServices.IProductInterface;
import tn.esprit.teriak.Repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService implements IProductInterface {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product getProductByCodePCT(String codePCT) {
        return productRepository.findByCodePCT(codePCT);
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {
        Product existingProduct = getProductById(id);

        if (existingProduct == null) {
            return null;
        }

        existingProduct.setCodePCT(product.getCodePCT());
        existingProduct.setDesignation(product.getDesignation());
        existingProduct.setUniteParCaisse(product.getUniteParCaisse());
        existingProduct.setPrix(product.getPrix());
        existingProduct.setRemise(product.getRemise());
        existingProduct.setCarton(product.getCarton());
        existingProduct.setVrac(product.getVrac());
        existingProduct.setQuantiteTotale(product.getQuantiteTotale());
        existingProduct.setMontantApresRemise(product.getMontantApresRemise());
        existingProduct.setDisponible(product.isDisponible());
        existingProduct.setProductName(product.getProductName());

        return productRepository.save(existingProduct);
    }

    public boolean deleteProduct(Long id) {
        Product existingProduct = getProductById(id);

        if (existingProduct == null) {
            return false;
        }

        productRepository.delete(existingProduct);
        return true;
    }
}
