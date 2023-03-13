package com.mbeza.springmvc.services;

import com.mbeza.springmvc.domain.Customer;
import com.mbeza.springmvc.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("JpaIntegrationConfig.class")
@ActiveProfiles("jpadao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CustomerServiceJpaDaoImplTest {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Test
    public void testListMethod() throws Exception {
        List customerList = customerService.listAll();
        assert customerList.size() == 5;
    }

    @Test
    public void getCustomerByIdTest() {
        int id = 7;
        Customer testCustomer = new Customer();
        testCustomer.setId(id);

        assertEquals(testCustomer.getId(), customerService.getById(id).getId());
    }

    @Test
    public void updateCustomerTest() {
        Customer existCustomer = customerService.getById(6);
        List<Customer> customers = (List<Customer>) customerService.listAll();
        existCustomer.setCity("Lviv");
        assertEquals(existCustomer, customerService.saveOrUpdate(existCustomer));
    }

    @Test
    public void saveCustomerTest() {
        Customer newCustomer = Customer.newBuilder().setFirstName("alan")
                .setLastName("Balan")
                .setCity("Lviv")
                .setEmail("alan-balan@mail.com")
                .setPhoneNumber("9379992")
                .build();
        User user = new User();
        user.setUsername("test username");
        user.setPassword("some password");
        newCustomer.setUser(user);
        //here is an error related to one-to-one relationship with a User calss
        Customer insertedCustomer = customerService.saveOrUpdate(newCustomer);
        assertNotNull(insertedCustomer.getUser());
        assertEquals(newCustomer, insertedCustomer);

    }

    @Test
    public void deleteCustomerTest(){
        int idDeleting = 8;
        Customer toDelete = customerService.getById(idDeleting);
        assertNotNull(toDelete);

        customerService.delete(idDeleting);
        assertNull(customerService.getById(idDeleting));
    }
}
