package com.mbeza.springmvc.services.jpaservices;

import com.mbeza.springmvc.domain.Customer;
import com.mbeza.springmvc.services.CustomerService;
import com.mbeza.springmvc.services.security.EncryptedServiceImpl;
import com.mbeza.springmvc.services.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Service
@Profile("jpadao")
public class CustomerServiceJpaDaoImpl extends AbstractJpaDaoService implements CustomerService {
    EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService){
        this.encryptionService = encryptionService;
    }

    @PersistenceUnit
    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public List<Customer> listAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from Customer", Customer.class).getResultList();
    }

    @Override
    public Customer getById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Customer.class, id);
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        if (customer.getUser() != null && customer.getUser().getPassword() != null){
            customer.getUser().setPassword(encryptionService.encryptString(customer.getUser().getPassword()));
        }
        Customer savedCustomer = entityManager.merge(customer);
        entityManager.getTransaction().commit();
        entityManager.close();
        return savedCustomer;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Customer.class, id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
