package jpabook.jpashop.service;


import jpabook.jpashop.main.Address;
import jpabook.jpashop.main.Member;
import jpabook.jpashop.main.Order;
import jpabook.jpashop.main.OrderStatus;
import jpabook.jpashop.main.item.Book;
import jpabook.jpashop.main.item.Item;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;



    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setUsername("회원1");
        member.setAdress(new Address("서울", "강가", "123-123"));
        em.persist(member);


        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);


        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);


        //then
        Order getOrder = orderRepository.findOne(orderId);

        Assert.assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
        Assert.assertEquals("주문한 상품 종류 수가 정확해야 한다", 1, getOrder.getOrderItems().size());
        Assert.assertEquals("주문 가격은 가격 * 수량이다", 10000 * orderCount, getOrder.getTotalPrice());
        Assert.assertEquals("주문 수량만큼 재고가 줄어야 한다", 8, book.getStockQuantity());

    }

    @Test
    public void 주문취소() throws  Exception{
        //given

        //when

        //then
    }

    @Test
    public void 재고수량_초과() throws Exception{
        //given

        //when

        //then
    }



}