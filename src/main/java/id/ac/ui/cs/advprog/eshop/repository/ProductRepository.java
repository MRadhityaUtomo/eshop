package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
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

        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                productData.set(i, product);
            }
        }
        return product;
    }

    public Product deleteProduct(String id) throws NullPointerException{
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                Product product = productData.get(i);
                productData.remove(i);
                return product;
            }
        }
        throw new NullPointerException("Product not found");
    }
}
