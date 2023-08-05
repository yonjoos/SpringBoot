package jpabook.jpashop.repository;


import jpabook.jpashop.main.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){ //item 은 jpa에 저장하기 전까지 id값이 없다

        if(item.getId() == null){ //따라서 새로운 객체라는
            em.persist(item);
        }
        else{
            em.merge(item);
        }

    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }



}
