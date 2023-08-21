package study.datajpa.entity;

import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


@MappedSuperclass //속성만 물려줌
@Getter @Setter
public class JpaBaseEntity {

    @Column(updatable = false)
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;


    @PrePersist //persist하기 전에 하는 이벤트
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.updateDate = now;
    }

    @PreUpdate // before update
    public void preUpdate(){
        updateDate = LocalDateTime.now();
    }


}
