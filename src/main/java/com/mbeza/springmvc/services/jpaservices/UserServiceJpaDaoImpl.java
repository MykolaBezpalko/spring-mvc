package com.mbeza.springmvc.services.jpaservices;

import com.mbeza.springmvc.domain.User;
import com.mbeza.springmvc.services.UserService;
import com.mbeza.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class UserServiceJpaDaoImpl extends AbstractJpaDaoService implements UserService {

    EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Override
    public List<?> listAll() {
        EntityManager entityManager = emf.createEntityManager();

        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();

        return entityManager.find(User.class, id);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        User savedUser = entityManager.merge(domainObject);
        entityManager.getTransaction().commit();

        return savedUser;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(User.class, id));
        entityManager.getTransaction().commit();
    }
}
