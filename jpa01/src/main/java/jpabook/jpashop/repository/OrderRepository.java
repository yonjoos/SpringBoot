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
    public List<Order> findAll(OrderSearch orderSearch) {

        String jpql = "select o from Order o join o.member m";
        boolean isFirstCondition = true;

        //어차피 안 된다.
        //동적쿼리
        //문자 더하기로 하는거 귀찮다

        return em.createQuery(jpql, Order.class)
                .setMaxResults(1000)
                .getResultList();


    }

}
