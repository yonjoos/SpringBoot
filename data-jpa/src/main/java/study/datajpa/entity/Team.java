package study.datajpa.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Team {

    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;


    /*
    ##################### 연관관계 매핑 ##########################################
     */
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();


    /*
    ##################### CONSTRUCTOR ##########################################
     */

    public Team(String name) {
        this.name = name;
    }
}
