package com.mbeza.springmvc.services.mapservices;

import com.mbeza.springmvc.domain.DomainObject;
import com.mbeza.springmvc.domain.User;
import com.mbeza.springmvc.services.UserService;

import java.util.List;

public class UserServiceMapImpl extends AbstractMapService implements UserService {



    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public User getById(Integer id) {
        return (User) super.getById(id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        System.out.println("user map service save or update");
        return (User) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Integer id) {
        super.delete(id);
    }
}