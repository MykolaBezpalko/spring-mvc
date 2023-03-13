package com.mbeza.springmvc.services.mapservices;

import com.mbeza.springmvc.domain.DomainObject;
import com.mbeza.springmvc.domain.Product;
import com.mbeza.springmvc.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    public ProductServiceImpl() {

    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Integer id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
       return (Product) super.saveOrUpdate(product);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }

}
