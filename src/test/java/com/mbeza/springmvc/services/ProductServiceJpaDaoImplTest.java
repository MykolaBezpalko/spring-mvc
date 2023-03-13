package com.mbeza.springmvc.services;

import com.mbeza.springmvc.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("JpaIntegrationConfig.class")
@ActiveProfiles("jpadao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ProductServiceJpaDaoImplTest {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }



    @Test
    public void testListMethod() throws Exception {
        List<Product> productList = (List<Product>) productService.listAll();
        assertEquals(5, productList.size());
    }

    @Test
    public void getProductByIdTest() throws Exception {
        Integer id = 3;
        Product testProduct = new Product();
        testProduct.setId(id);

        assertEquals(testProduct.getId(), productService.getById(id).getId());
    }

    @Test
    public void updateProductTest() {
        Product existProduct = productService.getById(4);
        existProduct.setDescription("test description for exist product");

        assertEquals(existProduct, productService.saveOrUpdate(existProduct));
    }

    @Test
    public void saveProductTest() {
        Product newProduct = new Product();
        newProduct.setDescription("test description for new product");

        Product insertedProduct = productService.saveOrUpdate(newProduct);
        assertEquals(newProduct, insertedProduct);
    }

    @Test
    public void deleteProductTest(){
        int idDeleting = 1;
        Product toDelete = productService.getById(idDeleting);
        assertNotNull(toDelete);

        productService.delete(idDeleting);
        assertNull(productService.getById(idDeleting));
    }
}