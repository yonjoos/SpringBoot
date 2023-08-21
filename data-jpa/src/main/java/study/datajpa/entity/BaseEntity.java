package study.datajpa.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) //이거 쓰기 귀찮으면 xml 파일에 추가해도 됨. 근데 그게 더 귀찮
@MappedSuperclass
@Getter
public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;


    /*
    아래 두개는, main함수에 등록한 bean이 값을 채워 준다는데
     */

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;


    @LastModifiedBy
    private String lastModifiedBy;
}
