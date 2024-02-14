package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {
    MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void testCreateProductPageMock() throws Exception {
        mockMvc.perform(get("/product/create"))
               .andExpect(status().isOk())
               .andExpect(view().name("CreateProduct"))
               .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPostMock() throws Exception {
        Product product = new Product();
        doReturn(product).when(productService).create(any(Product.class));

        mockMvc.perform(post("/product/create")
                .param("productName", "Test Product")
                .param("productQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    void testEditProductPageMock() throws Exception {
        Product product = new Product();
        when(productService.findById(anyString())).thenReturn(product);

        mockMvc.perform(get("/product/edit/{productId}", "eb558e9f-1c39-460e-8860-71af6af63bd6"))
               .andExpect(status().isOk())
               .andExpect(view().name("EditProduct"))
               .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("CreateProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = productController.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService).create(product);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.findAll()).thenReturn(productList);

        String viewName = productController.productListPage(model);
        assertEquals("ProductList", viewName);
        verify(model).addAttribute("products", productList);
    }

    @SuppressWarnings("null")
    @Test
    void testEditProductPage() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        when(productService.findById(productId)).thenReturn(product);

        String viewName = productController.editProductPage(productId, model);
        assertEquals("EditProduct", viewName);
        verify(model).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        String viewName = productController.editProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(productService).save(product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "someProductId";
        String viewName = productController.deleteProductPost(productId, model);
        assertEquals("redirect:../list", viewName);
        verify(productService).deleteProduct(productService.findById(productId));
    }
    
    @Test
    void testDeleteProductPost() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Product product = new Product();
        product.setProductId(productId);
        when(productService.findById(productId)).thenReturn(product);

        for (int i = 0; i < 5 ; i++){
            mockMvc.perform(get("/product/delete/{productId}", productId))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("../list"));
        }
        verify(productService, times(5)).deleteProduct(product);
    }

    @Test
    void testDeleteNonExistentProduct() throws Exception {
        String nonExistentProductId = "111111";
        when(productService.findById(nonExistentProductId)).thenReturn(null);

        mockMvc.perform(get("/product/delete/" + nonExistentProductId))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("../list"));

        verify(productService, never()).deleteProduct(any(Product.class));
    }

}
