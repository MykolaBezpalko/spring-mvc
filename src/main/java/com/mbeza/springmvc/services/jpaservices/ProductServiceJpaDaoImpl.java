package com.mbeza.springmvc.services.jpaservices;

import com.mbeza.springmvc.domain.Product;
import com.mbeza.springmvc.services.ProductService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpadao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

    @Override
    public List<Product> listAll() {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Integer id) {
        EntityManager entityManager = emf.createEntityManager();
        return entityManager.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product product) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        Product savedProduct = entityManager.merge(product);
        entityManager.getTransaction().commit();
        entityManager.close();
        return savedProduct;
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Product.class, id));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
