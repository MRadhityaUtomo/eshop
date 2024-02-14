package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(),savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct() {
        Product dummyProduct = new Product();
        dummyProduct.setProductId("512");
        dummyProduct.setProductName("Sampo Cap Bambang");
        dummyProduct.setProductQuantity(100);
        productRepository.create(dummyProduct);

        dummyProduct = productRepository.findById("512");
        dummyProduct.setProductName("Claymore");
        dummyProduct.setProductQuantity(50);
        productRepository.save(dummyProduct);

        dummyProduct = productRepository.findById("512");
        assertEquals(dummyProduct.getProductId(), "512");
        assertEquals(dummyProduct.getProductName(), "Claymore");
        assertEquals(dummyProduct.getProductQuantity(), 50);
    }

    @Test
    void testEditProductFailed() {
        Product dummyProduct = new Product();
        dummyProduct.setProductId("1");
        dummyProduct.setProductName("Sampo Cap Bambang");
        dummyProduct.setProductQuantity(100);
        productRepository.create(dummyProduct);

        assertThrows(NullPointerException.class, () -> productRepository.findById("512"));
    }
    
    @Test
    void testDeleteProduct(){
        Product dummyProduct = new Product();
        dummyProduct.setProductId("512");
        dummyProduct.setProductName("Sampo Penrose");
        dummyProduct.setProductQuantity(6);
        productRepository.create(dummyProduct);

        productRepository.deleteProduct("512");

        Iterator<Product> iterator = productRepository.findAll();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testDeleteMultiple(){
        Product dummyProduct1 = new Product();
        dummyProduct1.setProductId("512");
        dummyProduct1.setProductName("Sampo Penrose");
        dummyProduct1.setProductQuantity(1);
        productRepository.create(dummyProduct1);

        Product dummyProduct2 = new Product();
        dummyProduct2.setProductId("500");
        dummyProduct2.setProductName("Sabun Melati");
        dummyProduct2.setProductQuantity(1);
        productRepository.create(dummyProduct2);

        productRepository.deleteProduct("512");

        Iterator<Product> iterator = productRepository.findAll();
        boolean checker = true;
        while (iterator.hasNext()){
            if (iterator.next().getProductId().equals("512")){
                checker = false;
            }
        }
        assertTrue(checker);
    }

    @Test
    void testDeleteMultipleFailed(){
        Product dummyProduct1 = new Product();
        dummyProduct1.setProductId("512");
        dummyProduct1.setProductName("Sampo Penrose");
        dummyProduct1.setProductQuantity(1);
        productRepository.create(dummyProduct1);

        Product dummyProduct2 = new Product();
        dummyProduct2.setProductId("500");
        dummyProduct2.setProductName("Sabun Melati");
        dummyProduct2.setProductQuantity(1);
        productRepository.create(dummyProduct2);

        assertThrows(NullPointerException.class, () -> productRepository.deleteProduct("600"));

        Iterator<Product> iterator = productRepository.findAll();
        boolean checker = true;
        while (iterator.hasNext()){
            if (iterator.next().getProductId().equals("512")){
                checker = false;
            }
        }
        assertFalse(checker);
    }

    @Test
    void testDeleteFailed(){
        Product dummyProduct = new Product();
        dummyProduct.setProductId("512");
        dummyProduct.setProductName("Sampo Penrose");
        dummyProduct.setProductQuantity(1);
        productRepository.create(dummyProduct);

        assertThrows(NullPointerException.class, () -> productRepository.deleteProduct("500"));

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());
    }

}