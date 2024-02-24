package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepository {
    static int id = 0;
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        if(product.getProductId() == null){
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) throws NullPointerException{
        for (Product x : productData){
            if (x.getProductId().equals(id)){
                return x;
            }
        }
        throw new NullPointerException("Product not found");
    }

    public Product save(Product product) {
        String id = product.getProductId();
        Product requestedProduct = findById(id);
        requestedProduct.setProductName(product.getProductName());
        requestedProduct.setProductQuantity(product.getProductQuantity());
        return product;
    }

    public Product deleteProduct(String id) throws NullPointerException{
        Product product = new Product();
        boolean checker = false;
        Product requestedProduct = findById(id);
        if (requestedProduct != null){ checker = true; }
        if (checker) {
            productData.remove(requestedProduct);
            return product;
        }
        throw new NullPointerException("Product not found");
    }
}
