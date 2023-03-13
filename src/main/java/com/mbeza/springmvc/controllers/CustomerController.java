package com.mbeza.springmvc.controllers;


import com.mbeza.springmvc.domain.Customer;
import com.mbeza.springmvc.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping("/customers")
    public String customersList(Model model){
        model.addAttribute("customers", customerService.listAll());
        return "customer/customers";
    }

    @RequestMapping("/customer/{id}")
    public String getCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/customer";
    }

    @RequestMapping("customer/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model){
        model.addAttribute("customer", customerService.getById(id));
        return "customer/customerForm";
    }

    @RequestMapping(value = "/customer/new")
    public String newCustomer(Model model){
        model.addAttribute("customer", new Customer());
        return "customer/customerForm";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    public String saveOrUpdateCustomer(Customer customer){
        Customer savedCustomer = customerService.saveOrUpdate(customer);
        return "redirect:/customer/" + savedCustomer.getId();
    }

    @RequestMapping("/customer/delete/{id}")
    public String delete(@PathVariable Integer id){
        customerService.delete(id);
        return "redirect:/customers";
    }
}
