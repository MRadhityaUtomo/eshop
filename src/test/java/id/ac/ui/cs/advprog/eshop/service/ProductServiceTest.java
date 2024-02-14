package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @InjectMocks
    ProductServiceImpl service;

    @Mock
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testEditProductByService() {
        Product dummyProduct = new Product();
        dummyProduct.setProductName("Sampo Cap Bambang");
        dummyProduct.setProductQuantity(100);
        service.create(dummyProduct);
        
        dummyProduct.setProductName("Claymore");
        dummyProduct.setProductQuantity(50);
        service.save(dummyProduct);

        assertEquals(dummyProduct.getProductId(), "1");
        assertEquals(dummyProduct.getProductName(), "Claymore");
        assertEquals(dummyProduct.getProductQuantity(), 50);
    }

    @Test
    void testFindAllIfEmptyService() {
        List<Product> productList = Arrays.asList();
        when(productRepository.findAll()).thenReturn(productList.iterator());
        List<Product> productIterator = service.findAll();
        assertTrue(productIterator.isEmpty());
    }

    @Test
    void testFindAllService() {
        Product dummyProduct = new Product();
        dummyProduct.setProductName("Sampo Cap Bambang");
        dummyProduct.setProductQuantity(100);

        List<Product> productList = Arrays.asList(dummyProduct);
        when(productRepository.create(dummyProduct)).thenReturn(dummyProduct);
        when(productRepository.findAll()).thenReturn(productList.iterator());
        service.create(dummyProduct);
        List<Product> productIterator = service.findAll();
        
        assertTrue(productIterator != null);
    }

    @Test
    void testFindByIdService() {
        Product product = new Product();
        product.setProductId("512");
        product.setProductName("Claymore");
        product.setProductQuantity(100);
        product.getProductId();
        productRepository.create(product);

        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product dummyProduct = service.findById("512");

        assertEquals(dummyProduct.getProductId(), "512");
        assertEquals(dummyProduct.getProductName(), "Claymore");
        assertEquals(dummyProduct.getProductQuantity(), 100);
    }
    
    @Test
    void testDeleteService() {
        Product product = new Product();
        product.setProductId("512");
        product.setProductName("Claymore");
        product.setProductQuantity(100);
        product.getProductId();
        productRepository.create(product);
        when(productRepository.deleteProduct(product.getProductId())).thenReturn(product);

        Product deletedProduct = service.deleteProduct(product); 
        
        verify(productRepository, times(1)).deleteProduct(product.getProductId());

        assertEquals("Claymore", product.getProductName());
        assertEquals(100, product.getProductQuantity());
        assertEquals("512", deletedProduct.getProductId());
        
    }
}