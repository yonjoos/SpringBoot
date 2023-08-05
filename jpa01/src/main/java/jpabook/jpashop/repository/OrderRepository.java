package jpabook.jpashop.repository;


import jpabook.jpashop.main.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class, id);
    }


    //나중에 더 설명하겠음
    //public List<Order> findAll(OrderSearch orderSearch) {}

}
