package hellojpa;


import javax.persistence.*;
import java.util.Date;

@Entity //JPA가 로딩될 때 JPA를 사용하는 애구나
@SequenceGenerator(
        name = "member_seq_generator",
        sequenceName = "member_seq"
)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                generator = "member_seq_generator")
    private Long id;

    @Column(name = "name", nullable = false)
    private String username;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
