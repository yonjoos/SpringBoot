package jpabook.jpashop.service;


import jpabook.jpashop.exception.NotEnoughStockException;
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

import static org.junit.jupiter.api.Assertions.fail;

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
        Member member = createMember();



        Book book = createBook("시골", 10000, 10);



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



    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량초과() throws Exception{
        //given
        Member member = createMember();
        Item item = createBook("시골", 10000, 10);

        int orderCount = 11; //exception 생겨야함


        //when
        orderService.order(member.getId(), item.getId(), orderCount);

        //then
        fail("재고수량 예외 생겨야함");

    }

    @Test
    public void 주문취소() throws  Exception{
        //given
        Member member = createMember();
        Item item = createBook("시골 JPA", 10000, 10);

        int orderCount = 2; //두 권 주문
        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        //then
    }

    @Test
    public void 재고수량_초과() throws Exception{

    }



    //command + opt + M
    private Book createBook(String name, int price, int stockQuantity) { //command + opt + P
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member createMember() {
        Member member = new Member();
        member.setUsername("회원1");
        member.setAdress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }



}