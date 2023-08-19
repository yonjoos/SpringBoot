package jpabook2.jpashop2.repository;


import jpabook2.jpashop2.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public OrderRepository(EntityManager em){
        this.em = em;
    }

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }


    /*
    public List<Order> findAll(){
        return em.createQuery("select o from Order o", Order.class).getResultList();
    }

     */

}
