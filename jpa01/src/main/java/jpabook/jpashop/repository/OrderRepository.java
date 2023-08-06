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


    //편하게 들어라, 어차피 안 쓴다
    //public List<Order> findAllByCriteria(OrderSearch orderSearch){

    //}

    //쿼리 칠 때 오타 나는거 어케 해결? ::: 쿼리 DSL

    
}
