package hellojpa;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity //JPA가 로딩될 때 JPA를 사용하는 애구나
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    //@Column(name = "TEAM_ID")
    //private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;




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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
