package jpabook.jpashop;


import jpabook.jpashop.main.*;
import jpabook.jpashop.main.item.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class InitDb {

    private final InitService initService;
    @Autowired
    public InitDb(InitService initService){
        this.initService = initService;
    }

    @PostConstruct //이기 머꼬??
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
    }

    // ################################################

    @Component
    @Transactional
    static class InitService{

        @PersistenceContext
        private final EntityManager em;

        @Autowired
        public InitService(EntityManager em){
            this.em = em;
        }

        public void dbInit1(){
            Member member = new Member();
            member.setUsername("userA");
            member.setAddress(new Address("서울", "1", "1111"));
            em.persist(member);

            Book book1 = new Book();
            book1.setName("JPA1 BOOK");
            book1.setPrice(10000);
            book1.setStockQuantity(100);

            Book book2 = new Book();
            book2.setName("JPA2 BOOK");
            book2.setPrice(20000);
            book2.setStockQuantity(100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }


        public void dbInit2(){
            Member member = new Member();
            member.setUsername("userB");
            member.setAddress(new Address("제주", "2", "2222"));
            em.persist(member);

            Book book1 = new Book();
            book1.setName("SPRING1 BOOK");
            book1.setPrice(10000);
            book1.setStockQuantity(100);

            Book book2 = new Book();
            book2.setName("SPRING2 BOOK");
            book2.setPrice(20000);
            book2.setStockQuantity(100);

            em.persist(book1);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }


    }

}


