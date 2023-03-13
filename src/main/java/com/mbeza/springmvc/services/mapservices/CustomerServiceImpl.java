package com.mbeza.springmvc.services.mapservices;

import com.mbeza.springmvc.domain.Customer;
import com.mbeza.springmvc.domain.DomainObject;
import com.mbeza.springmvc.services.mapservices.AbstractMapService;
import com.mbeza.springmvc.services.CustomerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("map")
public class CustomerServiceImpl extends AbstractMapService implements CustomerService {
    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Customer getById(Integer id) {
        return (Customer) super.getById(id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        return (Customer) super.saveOrUpdate(customer);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}
