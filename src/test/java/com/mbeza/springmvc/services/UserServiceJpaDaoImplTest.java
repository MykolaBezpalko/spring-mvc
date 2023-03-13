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

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest("JpaIntegrationConfig.class")
@ActiveProfiles("jpadao")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class UserServiceJpaDaoImplTest {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void testSaveOfUser() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("myPassword");
        User savedUser = userService.saveOrUpdate(user);
        assert savedUser.getId() != null;
        assert savedUser.getEncryptedPassword() != null;
    }

    @Test
    public void testSaveOfUserWithCustomer() throws Exception {
        User user = new User();
        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Nova");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCustomer());
        assertNotNull(savedUser.getCustomer().getId());
    }
}
