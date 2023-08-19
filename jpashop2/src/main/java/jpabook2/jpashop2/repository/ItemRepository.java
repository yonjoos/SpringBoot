package jpabook2.jpashop2.repository;


import jpabook2.jpashop2.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {

    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public ItemRepository(EntityManager em){
        this.em = em;
    }

    public void save(Item item){
        if(item.getId() == null){ //item은 첨에 생성될 때 아이디가 없다, 그래서 id가 없으면 추가해라
            em.persist(item); //Member같은 경우에는 이미 있으면 throw Exception 해버리기 때문에 이 과정 생략
        }else{
            em.merge(item);
        }
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class).getResultList();
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }
}
