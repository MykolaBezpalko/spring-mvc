package com.mbeza.springmvc.bootstrap;

import com.mbeza.springmvc.domain.Customer;
import com.mbeza.springmvc.domain.Product;
import com.mbeza.springmvc.services.CustomerService;
import com.mbeza.springmvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private ProductService productService;
    private CustomerService customerService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadProducts();
        loadCustomers();
    }

    private void loadProducts() {

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");
        productService.saveOrUpdate(product1);

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");
        productService.saveOrUpdate(product2);

        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");
        productService.saveOrUpdate(product3);

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");
        productService.saveOrUpdate(product4);

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("52.99"));
        product5.setImageUrl("http://example.com/product5");
        productService.saveOrUpdate(product5);
    }

    private void loadCustomers() {

        int numberOfCustomers = 5;
        for (int i = 1; i <= numberOfCustomers; i++) {
            Customer dummy = Customer.newBuilder()
                    .setCity("City" + i)
                    .setEmail("Email" + i)
                    .setAddressLineOne("Address line One - " + i)
                    .setAddressLineTwo("Address line Two - " + i)
                    .setFirstName("Name" + i)
                    .setLastName("Surname" + i)
                    .setZipCode("zip code " + i)
                    .setPhoneNumber("0000 000" + i)
                    .build();
                    customerService.saveOrUpdate(dummy);

        }
    }
}
