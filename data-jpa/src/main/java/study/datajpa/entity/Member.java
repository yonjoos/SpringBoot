package study.datajpa.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private int age;


    /*
    ################################## 연관관계 매핑 ####################################
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    /*
    ################################## 연관관계 setting 메서드 ####################################
     */
    public void changeTeam(Team team){
        this.team = team;
        team.getMembers().add(this);
    }




    /*
    ################################## 생성자 ####################################
     */

    public Member(String username) {
        this.username = username;
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(this.team != null){
            changeTeam(team);
        }
    }
}
