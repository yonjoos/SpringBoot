package study.datajpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.datajpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void save(){
        Item item = new Item("A");
        //PK값이 설정됐기 때문에 persist 호출이 안 된다, 왜냐하면 null이어야 새 객체로 받아들이니까
        itemRepository.save(item);

    }

}