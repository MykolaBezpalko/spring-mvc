package com.mbeza.springmvc.controllers;

import com.mbeza.springmvc.domain.Product;
import com.mbeza.springmvc.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class ProductControllerTest {
    Product p1 = new Product();
    Product p2 = new Product();
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        p1.setId(1);
        p2.setId(2);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testList() throws Exception {

        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);

        when(productService.listAll()).thenReturn((List) products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void testProduct() throws Exception {
        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("product/product"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void editTest() throws Exception {
        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testNewProduct() throws Exception {
        Integer id = 1;

        verifyNoMoreInteractions(productService);

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String description = "Test Description";
        BigDecimal price = new BigDecimal("12.00");
        String imageUrl = "example.com";

        Product returnProduct = new Product();
        returnProduct.setId(id);
        returnProduct.setDescription(description);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);


        when(productService.saveOrUpdate(refEq(returnProduct))).thenReturn(returnProduct);

        mockMvc.perform(post("/product")
                        .param("id", "1")
                        .param("description", description)
                        .param("price", "12.00")
                        .param("imageUrl", "example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/1"))
                .andExpect(model().attribute("product", instanceOf(Product.class)))
                .andExpect(model().attribute("product", hasProperty("id", is(id))))
                .andExpect(model().attribute("product", hasProperty("description", is(description))))
                .andExpect(model().attribute("product", hasProperty("price", is(price))))
                .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(description, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageUrl());
    }

    @Test
    public void testDelete() throws Exception {
        Integer id = 1;
        mockMvc.perform(get("/product/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"));

        verify(productService, times(1)).delete(id);
    }

}
