package study.datajpa.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item implements Persistable<String> {

    @Id 
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    //private long id; 로 하면 long은 java의 primitive 이기 때문에 null 값을 가지지 못하고 대신 0

    public Item(String id){
        this.id = id;
    }

    @Override
    public boolean isNew(){ //이 entity가 새건지 있던건지 판단
        return createdDate == null;
    }
}
