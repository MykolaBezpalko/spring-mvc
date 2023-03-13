package com.mbeza.springmvc.controllers;

import com.mbeza.springmvc.domain.Customer;
import com.mbeza.springmvc.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Customer> customers = new ArrayList<>();
        Customer c1 = Customer.newBuilder()
                .setId(1)
                .build();
        Customer c2 = Customer.newBuilder()
                .setId(2)
                .build();

        customers.add(c1);
        customers.add(c2);

        when(customerService.listAll()).thenReturn((List) customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customers"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void testGetByIdCustomer() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));

    }

    @Test
    public void testEditCustomer() throws Exception {
        Integer id = 1;

        when(customerService.getById(id)).thenReturn(new Customer());

        mockMvc.perform(get("/customer/edit/" + id))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testNewCustomer() throws Exception {
        verifyNoMoreInteractions(customerService);

        mockMvc.perform(get("/customer/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerForm"))
                .andExpect(model().attribute("customer", instanceOf(Customer.class)));
    }

    @Test
    public void testSaveOrUpdate() throws Exception {
        Integer id = 1;
        String firstName = "test name";
        String lastName = "test last name";
        String email = "test email";
        String phoneNumber = "000";
        String addressLineOne = "test address 1";
        String addressLineTwo = "test address 2";
        String city = "test city";
        String zipCode = "test zip code";

        Customer returnCustomer = Customer.newBuilder()
                .setId(id)
                .setFirstName(firstName).setLastName(lastName)
                .setEmail(email).setPhoneNumber(phoneNumber)
                .setAddressLineOne(addressLineOne)
                .setAddressLineTwo(addressLineTwo)
                .setCity(city).setZipCode(zipCode)
                .build();

        when(customerService.saveOrUpdate(refEq(returnCustomer))).thenReturn(returnCustomer);

        mockMvc.perform(post("/customer")
                        .param("id", "1")
                        .param("firstName", firstName)
                        .param("lastName", lastName)
                        .param("email", email)
                        .param("phoneNumber", phoneNumber)
                        .param("addressLineOne", addressLineOne)
                        .param("addressLineTwo", addressLineTwo)
                        .param("city", city)
                        .param("zipCode", zipCode))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attribute("customer", instanceOf(Customer.class)))
                .andExpect(model().attribute("customer", hasProperty("id", is(id))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("customer", hasProperty("email", is(email))))
                .andExpect(model().attribute("customer", hasProperty("phoneNumber", is(phoneNumber))))
                .andExpect(model().attribute("customer", hasProperty("addressLineOne", is(addressLineOne))))
                .andExpect(model().attribute("customer", hasProperty("addressLineTwo", is(addressLineTwo))))
                .andExpect(model().attribute("customer", hasProperty("city", is(city))))
                .andExpect(model().attribute("customer", hasProperty("zipCode", is(zipCode))));

        ArgumentCaptor<Customer> boundCustomer = ArgumentCaptor.forClass(Customer.class);
        verify(customerService).saveOrUpdate(boundCustomer.capture());

        assertEquals(id, boundCustomer.getValue().getId());
        assertEquals(firstName, boundCustomer.getValue().getFirstName());
        assertEquals(lastName, boundCustomer.getValue().getLastName());
        assertEquals(email, boundCustomer.getValue().getEmail());
        assertEquals(phoneNumber, boundCustomer.getValue().getPhoneNumber());
        assertEquals(addressLineOne, boundCustomer.getValue().getAddressLineOne());
        assertEquals(addressLineTwo, boundCustomer.getValue().getAddressLineTwo());
        assertEquals(city, boundCustomer.getValue().getCity());
        assertEquals(zipCode, boundCustomer.getValue().getZipCode());
    }

    @Test
    public void testDelete() throws Exception {
        int id = 1;
        mockMvc.perform(get("/customer/delete/" + id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"));
    }
}
